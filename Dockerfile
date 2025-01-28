FROM tomcat:11-jre21

COPY app/build/libs/app.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080