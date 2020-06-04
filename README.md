# Stock Portfolio #

Small project using CQRS and event sourcing in Spring Boot with [Axos](https://axoniq.io/)
This project allows you to open positions, keep track of your portfolio based on events

## Prerequisites ##

Axos Server needs to be running.

## API

### Get My Portfolio ###

'''
GET - /api/v1/portfolios/me
'''

Returns your portfolio.

### Open Position ###

'''
POST - /api/v1/portfolios/me/positions
{
	"value": "ATT",
	"quantity": "4",
	"amount": "126.33",
	"currency": "USD"
}
'''

That will trigger a command that will be handled by Axos.

### Find My Positions ###

'''
GET - /api/v1/portfolios/me
'''

Returns all your positions.
