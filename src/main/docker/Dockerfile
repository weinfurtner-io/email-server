FROM java:8
MAINTAINER jared@weinfurtner.io
ADD email-server-0.0.1-SNAPSHOT.jar email-server.jar
RUN sh -c 'touch /email-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/email-server.jar"]