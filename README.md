# SpringBootBasicAuthTypeByProfile
Voll funktionsfähiges Beispiel zur profilbasierten Auswahl der Art der Authentifizierung:

1. via JDBC gegen embedded H2
2. via DAO gegen embedded H2
3. gegen embedded LDAP
4. gegen InMemory (= hartkodierter Benutzername/passwort, könnte z.B. aus Konfigurationsdatei kommen) 

Siehe MySecurityConfigurer für Konfiguration der Basic Auth.
Für profilabhängige Konfiguration der Datenquelle für Benutzerinformationen siehe Unterpackes jdbc, ldap und inmemory.
Die Konfiguration erfolgt hier jedenfalls in der Klasse AuthenticationConfiguration...

Die Hauptklasse der Anwendung ist _MyApplication.class_.

## Start der Anwendung

Siehe [Running your application](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html)

*Profile:*

Das aktive Profil lässt sich bei Spring Boot setzen mittels VM-Argument (spring.profiles.active).
In unserem Beispiel:

1. ``-Dspring.profiles.active=jdbc`` _(für JDBC)_
2. ``-Dspring.profiles.active=dao`` _(für DAO)_ 
3. ``-Dspring.profiles.active=ldap`` _(für LDAP)_ 
4. ``-Dspring.profiles.active=default`` _(oder ohne VM Argument für InMemory)_

*Mehrere Profile:*

Es ist auch möglich mehrere Profile kommasepariert anzugeben z.B. default,jdbc,dev 
(in diesem Bsp. würde dann die Authentifizierung gegen InMemory und die Authentifizierung via JDBC zur Verfügung stehen).

*"dev" Profil:*

Setzt man zusätzlich das Profil "dev" steht auch die H2-Konsole unter _http://localhost:8080/h2-console_ zur Verfügung,
mit der man während der Entwicklung einen Blick in die H2 Datenbank werfen kann. 
Logindaten siehe application.properties.


## Nach dem Start

Wenn die Applikation mit einem dieser Profile gestartet wurde kann man sich unter
_http://localhost:8080_ mit Basic Authentication einloggen.

1. Benutzernamen und Passwort bei JDBC _(siehe UserInit in Package user)_: ``ben/userFromDB``
2. Benutzernamen und Passwort bei DAO _(siehe UserInit in Package user)_: ``ben/userFromDB``
2. Benutzernamen und Passwort bei LDAP _(siehe src/main/resources/test-server.ldif)_ z.B.: ``ben/benspassword``
3. Benutzernamen und Passwort bei InMemory _(siehe AuthenticationConfigurationInMemory)_: ``user/inmemory`` 


## Quellen und Links

* [Spring Security 4: JDBC Authentication and Authorization in MySQL](https://dzone.com/articles/spring-security-4-authenticate-and-authorize-users)
* [Getting Started · Authenticating a User with LDAP - Spring](https://spring.io/guides/gs/authenticating-ldap/)
* [Getting Started · Securing a Web Application - Spring](https://spring.io/guides/gs/securing-web/)
* [Spring Security in Servlet Web Application using DAO, JDBC, In-Memory authentication](http://www.journaldev.com/2715/spring-security-in-servlet-web-application-using-dao-jdbc-in-memory-authentication)
