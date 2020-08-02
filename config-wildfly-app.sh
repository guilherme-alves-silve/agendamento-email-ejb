DIRETORIO_DE_SUA_MAQUINA/{jboss|wildfly}-[VERSAO]/bin/jboss-cli.bat --connect
module add --name=com.mysql --resources=/path/to/mysql-connector-java-8.0.15.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamento_email_db --user-name=agendamento_email --password=agendamento_email_password --min-pool-size=2 --max-pool-size=5
/subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
/subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref=my-smtp-binding, username=SEU-USUARIO-EMAIL, password=SUA-SENHA-EMAIL, tls=true)
jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue
