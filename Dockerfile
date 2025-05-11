
FROM openjdk:17-jdk-slim
WORKDIR /app

# Install netcat
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

COPY target/multi-tenant.jar app.jar
COPY wait-for-mysql.sh wait-for-mysql.sh
RUN chmod +x wait-for-mysql.sh
ENTRYPOINT ["./wait-for-mysql.sh", "java", "-jar", "app.jar"]

