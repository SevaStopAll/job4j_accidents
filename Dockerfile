FROM maven:3.6.3-openjdk-17

RUN mkdir school_app

WORKDIR school_app

COPY . .

RUN mvn package -Dmaven.test.skip=true

CMD ["mvn", "liquibase:update", "-Pdocker"]

CMD ["java", "-jar", "target/school_app-2.7.6.jar"]