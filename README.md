# SpringBootBasicAuthTypeByProfile
Fully functional example for profile-based selection of authentication type:

1. via JDBC using embedded H2
2. via DAO using embedded H2
3. via embedded LDAP
4. via configured username/password (inmemory) 

Die Hauptklasse der Anwendung ist _MyApplication.class_.

## Application start

See [Running your application](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html)

*Profile:*

The active profile can be set using the VM argument (spring.profiles.active).
In our example:


1. ``-Dspring.profiles.active=jdbc`` _(for JDBC)_
2. ``-Dspring.profiles.active=dao`` _(for DAO)_ 
3. ``-Dspring.profiles.active=ldap`` _(for LDAP)_ 
4. ``-Dspring.profiles.active=default`` _(or without VM Argument for inmemory)_

*Multiple profiles:*

It is also possible to specify several profiles separated by commas e.g. default,jdbc,dev 
(in this example the authentication against InMemory and the authentication via JDBC would be available).

*"dev" Profil:*

If you additionally set the profile "dev", the H2 console is also available at _http://localhost:8080/h2-console_,
which allows you to have a look at the H2 database during development. 
For login data see application.properties.

## After starting the application

If the application was started with one of these profiles, you can log on under
_http://localhost:8080/protected_ with Basic Authentication.

1. Username and password for JDBC _(see UserInit)_: ``ben/userFromDB``
1. Username and password for DAO _(see UserInit)_: ``ben/userFromDB``
1. Username and password for LDAP _(see src/main/resources/test-server.ldif)_ z.B.: ``ben/benspassword``
1. Username and password for Inmemory _(see InmemoryAuthenticationConfiguration and application.properties)_: ``myuser/a`` 

Please note that the configuration provided in application.properties can be overridden e.g. by passing program arguments, see [Spring documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files).

## Quellen und Links

* [Spring Security 4: JDBC Authentication and Authorization in MySQL](https://dzone.com/articles/spring-security-4-authenticate-and-authorize-users)
* [Getting Started · Authenticating a User with LDAP - Spring](https://spring.io/guides/gs/authenticating-ldap/)
* [Getting Started · Securing a Web Application - Spring](https://spring.io/guides/gs/securing-web/)
* [Spring Security in Servlet Web Application using DAO, JDBC, In-Memory authentication](http://www.journaldev.com/2715/spring-security-in-servlet-web-application-using-dao-jdbc-in-memory-authentication)
