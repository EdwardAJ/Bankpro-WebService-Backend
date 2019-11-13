FROM tomcat:9-jdk11
LABEL maintainer="13517115@std.stei.itb.ac.id"
EXPOSE 8080
COPY target/wsbank.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]