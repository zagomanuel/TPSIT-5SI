FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY src ./src
COPY data ./data

RUN javac -d out $(find src -name "*.java")

CMD ["java", "-cp", "out", "Main", "data/studenti.xml", "dom"]
