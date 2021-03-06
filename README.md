### CODE STANDARDS

We follow the rules
from [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) and:

- The interfaces should start with prefix "I". Example: IUserRepository.
- The names of the attributes for Java code use camel case, but the name for SQL uses underscore and
  uppercase.
- The name of the tables should be in plural, but the entity name should be in singular.
- Exceptions should be handled by ErrorHandler class.
- All the configurations must be in the config package.
- The name of abstract classes should start with prefix "Abstract". Example: "AbstractFile".
- The integration test should go into the integration package.
- If you add a new endpoint, make sure to set the role access for it in the SecurityConfig class.

The code style for this repository is the used by [Google](https://github.com/google/styleguide).
Make sure to set up your IDE with the right code style format file.

### KEEP IN MIND FOR PULL REQUEST AND CODE REVIEW

- The branch name should be the {ticket#}.
- The rule for pull request title is: "{ticket#}: {jiraTitle}".
- The commits should follow this pattern: "{ticket#}: {commitDescription}". Small commits are a nice
  to have.
- If you don’t add unit test or integration test as part of your code changes, you should add the
  request and response as evidence that the code is working as expected.
- Postman collection should be updated every time that you open a pull request. Be a team player!
- Once you finish to addressing all the comments, leave a comment on the pull request to the
  reviewer asking to re-review the PR.

### PROJECT SETUP

- Postman
- Maven
- JDK 11
- MySQL

To run the project execute: 

`mvn spring-boot:run`


### USERS SEED

| email              | role  |  
|--------------------|-------|
| ADMIN{id}@test.com | admin | 
| USER{id}@test.com  | user  | 

By default, 10 users with admin role and 10 users with user role will be created where the {id} in 
the email is a number from 0 to 10 per role. All the users have "test1234" as password.

### ROLES SEED

| id |  description |  name   |
|----|--------------|---------| 
| 1  |  ROLE_USER   |  USER   |
| 2  |  ROLE_ADMIN  |  ADMIN  | 
