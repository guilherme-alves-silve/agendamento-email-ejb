# Agendamento E-mail

## Baseado no curso [EJB com Java EE 8: API Rest com WildFly 15](https://cursos.alura.com.br/course/java-ee-8-e-ejb-3) do Alura

### Tecnologias utilizadas
- Java 11+
- Wildfly
- JMS
- JPA
- Docker
- MySQL

### Executar o JBoss/Wildfly no docker - [link](https://registry.hub.docker.com/r/jboss/wildfly#!)

``
docker run -p 8080:8080 -p 9990:9990 -it jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
``

### Executar o MySQL - [link](https://hub.docker.com/_/mysql/)

``
docker-compose up
``