FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY target/*.jar /opt/app/mongoreporting.jar
CMD ["java", "-jar", "/opt/app/mongoreporting.jar"]