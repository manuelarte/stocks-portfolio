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

## API

### Create account ###

```
POST - /api/v1/users/{userId}
```


### Open Position ###

```
POST - /api/v1/portfolios/{userId}/positions/{symbol}
{
	"quantity": "4",
	"amount": "126.33",
	"currency": "USD",
	"timestamp": "2020-06-05T13:00:00Z"
}
```

That will trigger a command that will be handled by AxonIQ.

### Close Position ###

```
DELETE - /api/v1/portfolios/{userId}/positions/{symbol}
{
	"quantity": "4",
	"amount": "126.33",
	"currency": "USD",
	"timestamp": "2020-06-05T13:00:00Z"
}
```

That will trigger a command that will be handled by AxonIQ.

### Find User's Positions ###

```
GET - /api/v1/portfolios/{userId}/positions[?size=10&page=0]
```

Returns all your positions.
