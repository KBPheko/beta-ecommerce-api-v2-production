FROM eclipse-temurin:8-jdk

COPY target/beta-ecommerce-api-v2-0.0.1-SNAPSHOT.jar beta-ecommerce-api-v2-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/beta-ecommerce-api-v2-1.0.0.jar"]