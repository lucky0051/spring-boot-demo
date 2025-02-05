FROM tomcat :8.0.20-jre8
MAINTAINER Kshitish <kshitish@oracle.com>
EXPOSE 8080
COPY target/spring-boot-maven-plugin.war /usr/local/tomcat/webapps/spring-boot-maven-plugin.war
