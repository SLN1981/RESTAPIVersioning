package com.example.restapiversioning.versioning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation that specifies API version(s) for REST endpoints.
 * Version is matched from Accept header: application/json;v=N
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
    /** Supported API version numbers */
    int[] value();
}
