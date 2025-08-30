#!/bin/sh
# wait-for-port.sh host port
host="$1"
port="$2"
while ! nc -z "$host" "$port"; do
  echo "Waiting for $host:$port..."
  sleep 2
done