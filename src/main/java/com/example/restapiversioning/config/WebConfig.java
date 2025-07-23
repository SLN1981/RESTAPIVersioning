package com.example.restapiversioning.config;

import com.example.restapiversioning.versioning.ApiVersionRequestMappingHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Web configuration class that sets up the custom version-based request mapping.
 * This configuration is essential for the API versioning to work.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * Creates a custom request mapping handler that supports API versioning.
     * This overrides Spring's default handler mapping to include version support.
     *
     * @return ApiVersionRequestMappingHandlerMapping instance for version-based routing
     */
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }
}
