#!/usr/bin/env bash

db_dir=$(cd $(dirname $0); pwd)

# Start (up) postgres container.
docker compose -f $db_dir/bookworms-postgres-13.yml up -d