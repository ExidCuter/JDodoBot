FROM amazoncorretto:11

RUN mkdir /opt/JDodoBot
WORKDIR /opt/JDodoBot

COPY . .

RUN ./gradlew build -x test

EXPOSE 8080
CMD ["/opt/JDodoBot/gradlew", "bootRun"]
