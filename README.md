# Bookworms Demo Application 
This application serves a dual purpose. It demonstrates 1) a basic web application built with Scala
and React.JS; and 2) instrumentation of an A/B test and a feature flag with 
[Variant CVM](https://getvariant.dev).

### Table of Contents

### 1. Downloading and Running

#### Clone this repository 
```shell
$ git clone git@github.com:iurisman/bookworms.git
```

#### Create the Postgres Database
```shell
$ cd bookworms/src/test/db
```
To run with containerized Postgres:
Ensure Docker Desktop is running
```shell
./postgres-down.sh
./schema.sh
```
This will:
* Deploy Postgres 13 in a Docker container with the root user `postgres` listening on port `5432`;
* Create user `bookworms` with password `bookworms`;
* Create database `bookworms` owned by user `bookworms`;
* Create application schema in the database `bookworms`.

If you change any of these setting, be sure to update `bookworms/src/main/resources/application.conf`

If you'd rather run with a locally installed Postgres, any recent version should do. It should be
easy to adopt `postgres-up.sh` and `schema.sh` for the local case.

#### Run Unit Tests on API Server (Optional)
```shell
$ cd bookworms
$ sbt test
```

#### Run API Server
```shell
$ sbt run
```
This will start the Bookworms API server on `localhost:8080`.

#### Run Node Frontend
```shell
$ cd bookworms/node
$ npm install
```
This will download required Node modules. You should only have to do it once on a newly cloned
repository. To start Node:
```shell
$ npm start
```
Point your browser `localhost:3000`.

