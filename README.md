# Number Classification API

This project implements a RESTful API using Spring Boot that classifies numbers and returns interesting mathematical properties along with a fun fact.  It fulfills the requirements of a technical assessment, demonstrating best practices in code structure, error handling, and deployment readiness.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [API Specification](#api-specification)
- [Code Structure](#code-structure)
- [Technologies Used](#technologies-used)
- [Deployment](#deployment)


## Introduction

This API provides a robust and efficient way to retrieve mathematical information about a given integer. It is designed for seamless integration into various applications, delivering responses in a standardized JSON format.  This project emphasizes clean code, comprehensive error handling, and adherence to best practices for maintainability and scalability.

## Features

- Accepts `GET` requests with a `number` parameter.
- Returns a JSON response containing:
    - The input `number` (integer).
    - `is_prime`: Boolean indicating if the number is prime.
    - `is_perfect`: Boolean indicating if the number is perfect.
    - `properties`: An array of strings representing the number's properties (e.g., "armstrong", "odd", "even").
    - `digit_sum`: The sum of the digits of the number.
    - `fun_fact`: A fun fact about the number retrieved from the Numbers API (`http://numbersapi.com/`).
- Input validation to ensure the `number` parameter is a valid integer.
- Comprehensive error handling with appropriate HTTP status codes (400 Bad Request, 500 Internal Server Error).
- CORS (Cross-Origin Resource Sharing) enabled to allow requests from different domains.

## API Specification

**Endpoint:** `GET /api/classify-number?number=371`

**Request Parameter:**

- `number` (required): An integer.

**200 OK Response:**

```json
{
  "number": 371,
  "is_prime": false,
  "is_perfect": false,
  "properties": ["armstrong", "odd"],
  "digit_sum": 11,
  "fun_fact": "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371"
}
```
**400 Bad Request Response:**
```json        

{
  "number": "invalid_input",
  "error": true
}
```

## Technologies Used

    Java
    Spring Boot
    RestTemplate (with timeouts)
    Jackson (for JSON serialization/deserialization)

## Deployment
The application has been deployed to Railway: 
[Test the API](https://hngapi-production.up.railway.app/api/classify-number?number=123)
