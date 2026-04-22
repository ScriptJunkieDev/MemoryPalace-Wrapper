#!/bin/bash
set -e
mkdir -p /home/container/data
/home/container/pyenv/bin/python -m uvicorn python-adapter.main:app --host 127.0.0.1 --port 8099 > /home/container/data/adapter.log 2>&1 &
exec java -jar /home/container/app.jar
