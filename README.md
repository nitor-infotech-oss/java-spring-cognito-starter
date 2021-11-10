# java-spring-cognito-starter
This repo will contain code base for getting started with Spring Boot application with Cognito JWT verification. It will also contain other standard integration like Swagger and Liquibase
Cognito JWT verification:
Package : com.nitor.cognitostarter
 1. Javaspringcognitostarter.java - Provides Spring starter main(), @Bean ConfigurableJWTProcessor -Secure framework for application-specific processing of JSON Web Tokens (JWTs).
 ApiKey - code used to identify and authenticate an application or user
 SecurityContext - The SecurityContext and SecurityContextHolder are two fundamental classes of Spring Security. The SecurityContext is used to store the details of the currently authenticated user, also known as a principle.
 SecurityReference - Returns arraylist as list of authorization scopes
 SwaggerAPI - Use to configure swagger
 apiInfo - Swagger info details
 addCorsMappings - core mappings for curd operations
 2. WebSecurityConfig.java - Configured Websecurity Provided CSRF protection for any request that could be processed by a browser by normal users.antMatcher() tells Spring to only configure HttpSecurity if the path matches this pattern. The authorizeRequests(). antMatchers() is then used to apply authorization to one or more paths you specify in antMatchers() . Such as permitAll() or hasRole('USER3') . These only get applied if the first http.
Package : Application Exception 
 1. ApplicationException : com.nitor.cognitostarter.exception
Package : com.nitor.cognitostarter.security 
 1. AwsCognitoIdTokenProcessor : Provides token authorization includes JwtAuthentication, UserName, Role, Token
 2. AwsCognitoJwtAuthFilter :  This Spring Boot auto configuration module offers a security filter, capable of. Decoding an AWS Cognito JWT idToken. Verifying the JWT token signature. Verifying the JWT token issuer
 3. JwtAuthentication : Spring verifies credentials, and if they are valid, it generates a JWT and returns it.
 4. JwtConfiguration : JWTConfiguration specifies the configuration of a JWT authorizer
 5. JwtIdTokenCredentialsHolder : ID tokens are JSON web tokens (JWT).

 Use this coed as starter pack for spring project with AWS cognito security ready in state. we can modify the roles and access providing authority to the URL/APIs using @hasAuthority annotations. Used beared token as authentication purpouse.