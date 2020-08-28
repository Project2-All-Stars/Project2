FROM tomcat:8.5-jdk8-openjdk

COPY /target/project2.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
