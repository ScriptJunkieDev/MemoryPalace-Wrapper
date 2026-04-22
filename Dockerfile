FROM eclipse-temurin:21-jre

RUN apt-get update && apt-get install -y python3 python3-venv python3-pip && rm -rf /var/lib/apt/lists/*

WORKDIR /home/container

RUN python3 -m venv /home/container/pyenv
RUN /home/container/pyenv/bin/pip install --upgrade pip setuptools wheel
RUN /home/container/pyenv/bin/pip install fastapi uvicorn[standard] pydantic

COPY spring-api/target/memory-api-0.0.1-SNAPSHOT.jar /home/container/app.jar
COPY python-adapter/ /home/container/python-adapter/
COPY start.sh /home/container/start.sh

RUN chmod +x /home/container/start.sh

CMD ["/home/container/start.sh"]
