#!/usr/bin/env bash

db_dir=$(cd $(dirname $0); pwd)

# Stop and destroy (down) postgres container, but keep the volume with data.
docker compose -f $db_dir/bookworms-postgres-13.yml down

