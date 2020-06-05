# Stock Portfolio #

Small project using CQRS and event sourcing in Spring Boot with [AxonIQ](https://axoniq.io/)
This project allows you to open positions, keep track of your portfolio based on events.

## Prerequisites ##

AxonIQ Server needs to be running.

```
> java -jar axonserver*.java
```

## Workflow ##

To start your portfolio, first an user needs to be created, then, you can start opening and closing positions.
If you try to open a position without creating an account, an error will appear.

After reading the API section, you can start playing with the api by using the workflow_example.http file included in
 the repository.

## API

### Create account ###

```
POST - /api/v1/users/{userId}
```


### Open Position ###

Request:
```
POST - /api/v1/portfolios/{userId}/positions/{symbol}
Content-Type: application/json

{
	"quantity": "4",
	"amount": "126.33",
	"currency": "USD",
	"timestamp": "2020-06-05T13:00:00Z"
}
```

That will trigger a command that will be handled by AxonIQ.

### Close Position ###

Request:
```
DELETE - /api/v1/portfolios/{userId}/positions/{symbol}
Content-Type: application/json

{
	"quantity": "4",
	"amount": "126.33",
	"currency": "USD",
	"timestamp": "2020-06-05T13:00:00Z"
}
```

That will trigger a command that will be handled by AxonIQ.

### Find User's Positions ###

Returns a page with the user's positions.

Request:
```
GET - /api/v1/portfolios/{userId}/positions[?size=10&page=0]
```

Example of Response:
```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "content": [
        {
            "id": "0837508f-2eee-42c2-aa30-7d54fea11622",
            "symbol": "BBVA",
            "quantity": 1062
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "number": 0,
    "size": 10,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

Returns all your positions.
