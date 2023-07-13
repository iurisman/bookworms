#!/usr/bin/env bash

db_dir=$(cd $(dirname $0); pwd)

# Create 'bookworms' user and database.
docker exec -i bookworms-postgres-13 psql -U postgres <<EOF
  drop database if exists bookworms;
  drop user if exists bookworms;
  create database bookworms;
  \connect bookworms
  create user bookworms password 'bookworms';
EOF

# Create the database schema.
docker cp ${db_dir}/schema.sql bookworms-postgres-13:schema.sql
docker exec -i -e PGPASSWORD=bookworms bookworms-postgres-13  psql -U bookworms  -a -f schema.sql