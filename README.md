# SpringBootBasicAuthTypeByProfile
Voll funktionsfähiges Beispiel zur profilbasierten Auswahl der Art der Authentifizierung:

1. gegen JDBC (embedded H2)
2. gegen LDAP (embedded LDAP)
3. gegen InMemory (hartkodierter Benutzername/passwort) 


Siehe MySecurityConfigurer für Konfiguration der Basic Auth.
Für profilabhängige Konfiguration der Datenquelle für Benutzerinformationen siehe Unterpackes jdbc, ldap und inmemory.
Die Konfiguration erfolgt hier jedenfalls in der Klasse AuthenticationConfiguration...

Das vorliegende Beispiel basiert auf:
* [Spring Security 4: JDBC Authentication and Authorization in MySQL](https://dzone.com/articles/spring-security-4-authenticate-and-authorize-users)
* [Getting Started · Authenticating a User with LDAP - Spring](https://spring.io/guides/gs/authenticating-ldap/)
* [Getting Started · Securing a Web Application - Spring](https://spring.io/guides/gs/securing-web/)

Das aktive Profil lässt sich bei Spring Boot setzen mittels VM-Argument (spring.profiles.active).
In unserem Beispiel:

1. ``-Dspring.profiles.active=jdbc`` _(für JDBC)_
2. ``-Dspring.profiles.active=ldap`` _(für LDAP)_ 
3. ``-Dspring.profiles.active=default`` _(oder ohne VM Argument für InMemory)_ 

Es ist auch möglich mehrere Profile kommasepariert anzugeben z.B. default,jdbc (in diesem Bsp. würde dann die Authentifizierung gegen InMemory und JDBC zur Verfügung stehen).

Wenn die Applikation mit einem dieser Profile gestartet wurde kann man sich unter
http://localhost:8080 mit Basic Authentication einloggen.

1. Benutzernamen und Passwort bei JDBC _(siehe UserInit in Package jdbc)_: ``ben/jdbc``
2. Benutzernamen und Passwort bei LDAP _(siehe src/main/resources/test-server.ldif)_ z.B.: ``ben/benspassword``
3. Benutzernamen und Passwort bei InMemory _(siehe AuthenticationConfigurationInMemory)_: ``user/inmemory`` 

