
Requirements:
Java JRE 8

To run project:

1. Build it with command:
	mvnw compile
	
2. Run server:
	mvnw spring-boot:run
	
	
3. Configure mapping setups:

PUT http://localhost:8080/divisors/configuration
{
    "Animals": {
        "1":"Cat",
        "2":"Dog"
    },
    "Furnitures": {
        "1":"Table",
        "2":"Chair"
    }
}

4. Read mapped divisors:

GET http://localhost:8080/divisors/mapping?setup=Animals&numbers=1,2

Response:
{
    "1": [
        "Cat"
    ],
    "2": [
        "Cat",
        "Dog"
    ]
}