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


Users	Mail			First&LastName 	 Password
1	ADMIN0@test.com		Test	ADMIN0	 test1234
2	ADMIN1@test.com		Test	ADMIN1	 test1234
3	ADMIN2@test.com		Test	ADMIN2	 test1234
4	ADMIN3@test.com		Test	ADMIN3	 test1234
5	ADMIN4@test.com		Test	ADMIN4	 test1234
6	ADMIN5@test.com		Test	ADMIN5	 test1234
7	ADMIN6@test.com		Test	ADMIN6	 test1234
8	ADMIN7@test.com		Test	ADMIN7	 test1234
9	ADMIN8@test.com		Test	ADMIN8	 test1234
10	ADMIN9@test.com		Test	ADMIN9	 test1234
11	USER0@test.com		Test	USER0	 test1234
12	USER1@test.com		Test	USER1	 test1234
13	USER2@test.com		Test	USER2	 test1234
14	USER3@test.com		Test	USER3	 test1234
15	USER4@test.com		Test	USER4	 test1234
16	USER5@test.com		Test	USER5	 test1234
17	USER6@test.com		Test	USER6	 test1234
18	USER7@test.com		Test	USER7	 test1234
19	USER8@test.com		Test	USER8	 test1234
20	USER9@test.com		Test	USER9	 test1234
