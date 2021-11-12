# java-spring-cognito-starter
This repo will contain code base for getting started with Spring Boot application with Cognito JWT verification. It will also contain other standard integration like Swagger and Liquibase.

### Cognito Starter
Code provides starter for AWS cognito authentication to use this we have to provide configuration details as aws userPoolId and region details.Write service to get cognito ID, create user
[Congito](https://www.baeldung.com/spring-security-oauth-cognito)

### Swagger
Code Provides starter for Swagger which helps to group the APIs implemented, to use Swagger provide ApiInfo in JavaSpringCognitoStarter provide details of applications.Provide @ApiOperation Details to describe the API usage. And when we write APIs it will start reflecting on Url.
[SwaggerUrl] (http://localhost:port/swagger-ui.html#/)


### Liquibase:
Configured liquibase To use make sure liquibase enabled: true in application.properties and provide database connection details url,username,password in liquibase proerties and check logger name logback.xml. Set Environment(spring.profile.active/dev) to dev while running application in dev environment
[Liquibase](https://docs.liquibase.com/)

### Spring security
Spring security provide security while accessing each URL every time when you try to reach out to the Page or try to access website you have to povide ID and Password(Once on login or it will valid till session timeout). use @PreAuthorize, @hasAuhority to verify the user and role.
[SpringSecurity](https://spring.io/projects/spring-security)

Use this coed as starter pack for spring project with AWS cognito security ready in state. we can modify the roles and access providing authority to the URL/APIs using @hasAuthority annotations. Used beared token as authentication purpouse.