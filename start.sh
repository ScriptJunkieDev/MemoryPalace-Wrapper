#!/bin/bash
set -e
mkdir -p /home/container/data
cd /home/container/python-adapter
/home/container/pyenv/bin/python -m uvicorn main:app --host 127.0.0.1 --port 8099 > /home/container/data/adapter.log 2>&1 &
cd /home/container
exec java -jar /home/container/app.jar
