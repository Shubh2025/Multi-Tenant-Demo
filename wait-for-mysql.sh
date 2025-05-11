#!/bin/sh

echo "Waiting for MySQL to be available at $MYSQL_HOST:$MYSQL_PORT..."

until nc -z "$MYSQL_HOST" "$MYSQL_PORT"; do
  echo "MySQL is unavailable - sleeping"
  sleep 2
done

echo "MySQL is up - executing command"
exec "$@"
