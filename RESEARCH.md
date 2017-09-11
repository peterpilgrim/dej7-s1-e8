

# Dockerfile


    FROM payara/micro:173
    
    COPY  build/libs/ROOT.war   $DEPLOY_DIR




# docker-compose.yml



    pilgrimengineering:
      image: payara/micro:173
      ports:
        - 8080:8080
        - 8181:8181
        - 5900:5900
