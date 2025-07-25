FROM gradle:8.14.3-jdk21-alpine AS build
WORKDIR /build
COPY . .
RUN ./gradlew clean build

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /build/metis-server/build/libs/metis-server.jar /app/metis-server.jar
COPY --from=build /build/deployment/scripts/start-metis.sh /

RUN mkdir -p /opt/metis/plugins
COPY --from=build /build/metis-discord-eventsink/build/libs/metis-discord-eventsink.jar /opt/metis/plugins/metis-discord-eventsink.jar

ENTRYPOINT [ "sh" ]
CMD [ "/start-metis.sh" ]

