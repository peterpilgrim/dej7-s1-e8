FROM java:8-jdk-alpine
MAINTAINER peterpilgrim

ENV APP_LOCATION restful-stadia-services.jar
ENV APP_NAME application.jar

COPY $APP_LOCATION $APP_NAME
RUN chmod +x $APP_NAME

EXPOSE 8080
##EXPOSE 8181
##EXPOSE 5900

ENTRYPOINT java -jar $APP_NAME
