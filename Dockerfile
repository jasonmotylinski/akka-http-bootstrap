FROM hseeberger/scala-sbt

WORKDIR /

ADD target/scala-2.11/api-assembly-1.0.jar  /app/server.jar

ENTRYPOINT [ "java", "-jar", "/app/server.jar" ]

EXPOSE 8080