# REST API Versioning Demo

This project demonstrates API versioning in Spring Boot using Accept header strategy.

## Overview

This application showcases how to implement API versioning in a Spring Boot REST application using custom annotations and header-based version detection.

## Versioning Strategy

The application uses **Accept Header Versioning** where the version is specified in the Accept header:
```
Accept: application/json;v=1
```

### Benefits of this approach:
- Clean URLs without version numbers
- Flexible version selection through headers
- No URL pollution
- Supports multiple versions of the same endpoint

## Project Structure

- `ApiVersion.java`: Custom annotation for version specification
- `ApiVersionRequestMappingHandlerMapping.java`: Handler for version-based request routing
- Controllers:
  - `HelloController.java`: Method-level versioning example
  - `HelloUserV1Controller.java`: Class-level versioning for v1
  - `HelloUserV2Controller.java`: Class-level versioning for v2

## Endpoints

### Hello Endpoint
- V1: `GET /hello` - Basic greeting
- V2: `GET /hello` - Enhanced greeting

### User Endpoints
- V1:
  - `GET /greet` - Basic user greeting
  - `GET /welcome` - Simple welcome message
- V2:
  - `GET /greet` - Enhanced user greeting
  - `GET /welcome` - Enhanced welcome message

## Testing

A Postman collection is included (`REST_API_Versioning.postman_collection.json`) with pre-configured requests for all endpoints and versions.

### How to Test
1. Start the application (runs on port 8083)
2. Import the Postman collection
3. Execute requests with different version headers

Example curl commands:
```bash
# Version 1
curl -H "Accept: application/json;v=1" http://localhost:8083/hello

# Version 2
curl -H "Accept: application/json;v=2" http://localhost:8083/hello
```

## Technical Requirements
- Java 21
- Spring Boot 3.2.2
- Maven
