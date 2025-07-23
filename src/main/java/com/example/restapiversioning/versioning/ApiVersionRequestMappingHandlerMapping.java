package com.example.restapiversioning.versioning;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom request mapping handler that adds support for API versioning through HTTP Accept headers.
 * Extends Spring's RequestMappingHandlerMapping to provide version-based request routing.
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    /** Pattern to match version number in Accept header with format "application/json;v=N" */
    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("application/json;v=(\\d+)$");





    /**
     * Checks for @ApiVersion annotation at the class level and creates appropriate request conditions.
     * This allows versioning entire controller classes.
     *
     * @param handlerType the controller class to check for version annotation
     * @return RequestCondition for version matching, or null if no version specified
     */
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(typeAnnotation);
    }

    /**
     * Checks for @ApiVersion annotation at the method level and creates appropriate request conditions.
     * This allows versioning individual endpoints within a controller.
     *
     * @param method the controller method to check for version annotation
     * @return RequestCondition for version matching, or null if no version specified
     */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(methodAnnotation);
    }

    /**
     * Creates a version-based request condition from an @ApiVersion annotation.
     *
     * @param apiVersion the API version annotation to process
     * @return new ApiVersionRequestCondition if annotation exists, null otherwise
     */
    private RequestCondition<?> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionRequestCondition(apiVersion.value());
    }

    /**
     * Internal class that handles the version matching logic for requests.
     * Implements RequestCondition to integrate with Spring's request mapping system.
     */
    private static class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {
        private final int[] versions;

        /**
         * Creates a new condition that matches against the specified versions.
         *
         * @param versions array of valid version numbers for this condition
         */
        public ApiVersionRequestCondition(int[] versions) {
            this.versions = versions;
        }

        /**
         * Combines this condition with another condition.
         * In this implementation, we use the other condition's versions.
         *
         * @param other the other condition to combine with
         * @return a new condition combining both conditions
         */
        @Override
        public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
            return other != null && other.versions != null && other.versions.length > 0 ?
                    other : this;
        }

        /**
         * Checks if the request matches this version condition by examining the Accept header.
         * The Accept header must match the pattern "application/json;v=N" where N is a version number.
         *
         * @param request the HTTP request to check
         * @return this condition if the version matches, null otherwise
         */
        @Override
        public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader == null) {
                System.out.println("No Accept header found");
                return null;
            }

            Matcher matcher = VERSION_PREFIX_PATTERN.matcher(acceptHeader);
            if (!matcher.matches()) {
                System.out.println("Accept header doesn't match pattern: " + acceptHeader);
                return null;
            }

            int version = Integer.parseInt(matcher.group(1));
            System.out.println("Requested version: " + version + ", available versions: " + Arrays.toString(versions));
            for (int v : versions) {
                if (v == version) {
                    System.out.println("Version match found: " + v);
                    return this;
                }
            }
            System.out.println("No matching version found");
            return null;
        }

        /**
         * Compares this condition with another for precedence ordering.
         * Currently returns 0 as all version conditions have equal precedence.
         *
         * @param other the condition to compare with
         * @param request the current request
         * @return 0 indicating equal precedence
         */
        @Override
        public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
            return 0;
        }
    }
}
