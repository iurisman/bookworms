# Bookworms: Sample API Server Built With Scala & Akka HTTP

## Prerequisites
1. Install Java 11 JDK.
## Steps
1. Created and cloned git repository `bookworms`.
2. Created a seed application with 
```
sbt new akka/akka-http-quickstart-scala.g8
```
Use the suggested versions, as Akka does not support Scala 3 in production yet.

## Database Schema
![Bookworms ERD](Bookworms%20ERD.png)

## JSON Wire Protocol
### /books
#### GET /books
Returns a list of all books in catalog
```json
[
  {
    "id": "book id",
    "isbn": "isbn number",
    "title": "book title",
    "year": 1976,
    "copies": 3,
    "authors": [
      {
        "id": "author id",
        "name": "author name"
      }, ...
    ]
  }, ...
]
```

#### GET /books/{id}
Returns detailed information on a book.
```json
{
  "id": "book id",
  "isbn": "isbn number",
  "title": "book title",
  "year": 1976,
  "copies": [
    
  ],
  "authors": [
    {
      "id": "author id",
      "name": "author name"
    }, ...
  ]
}
```

### /copies

#### PUT /copies/{id}
Update a copy
Request:
```json
{
  "condition": "mint",
  "price": 12.34,
  "available": false
}
```

# Original Comments from the Akka Seed Application
This is a sample Akka HTTP endpoint keeping an in-memory database of users that can be created and listed.

Sources in the sample:

* `QuickstartApp.scala` -- contains the main method which bootstraps the application
* `UserRoutes.scala` -- Akka HTTP `routes` defining exposed endpoints
* `UserRegistry.scala` -- the actor which handles the registration requests
* `JsonFormats.scala` -- converts the JSON data from requests into Scala types and from Scala types into JSON responses

## Interacting with the sample

After starting the sample with `sbt run` the following requests can be made:

List all users:

    curl http://localhost:8080/users

Create a user:

    curl -XPOST http://localhost:8080/users -d '{"name": "Liselott", "age": 32, "countryOfResidence": "Norway"}' -H "Content-Type:application/json"

Get the details of one user:

    curl http://localhost:8080/users/Liselott

Delete a user:

    curl -XDELETE http://localhost:8080/users/Liselott