FROM openjdk:8

WORKDIR /app

COPY orderdelivery.jar /app/orderdelivery.jar

EXPOSE 8080

CMD ["java", "-jar", "orderdelivery.jar"]