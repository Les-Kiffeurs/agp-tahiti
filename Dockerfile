FROM tomcat:11-jre21

COPY docker/tomcat/conf/context.xml /usr/local/tomcat/conf/context.xml
COPY app/build/libs/app.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080