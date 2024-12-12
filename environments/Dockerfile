FROM docker-registry.uzabase.com/proxy-cache/library/clojure:temurin-21-lein-noble AS build

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app
RUN --mount=type=cache,target=/root/.m2 lein deps
RUN lein uberjar

FROM docker-registry.uzabase.com/proxy-cache/library/eclipse-temurin:21.0.4_7-jre-noble
USER user
WORKDIR /
COPY --chown=user:nogroup --from=build /usr/src/app/target/uberjar/status-analysis-of-recruitment-api-0.1.0-SNAPSHOT-standalone.jar /status-analysis-of-recruitment-api.jar

CMD ["java", "-jar", "status-analysis-of-recruitment-api.jar"]