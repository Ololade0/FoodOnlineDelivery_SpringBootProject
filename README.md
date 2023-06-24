# Food Delivery API

This is an API for a food delivery service, created using Spring Boot. It provides the necessary endpoints for browsing restaurants, viewing menus, placing orders, and handling order fulfillment.

## Features

The API provides the following features:

* Browsing restaurants
* Viewing restaurant menus
* Placing orders
* Handling order fulfillment
* Authentication and authorization for sensitive endpoints
* Validation and error handling for incoming requests

## Endpoints

- Browsing Restaurants

  - `GET /restaurants` - Returns a list of all restaurants.

- Viewing Menus

  - `GET /restaurants/{id}/menu` - Returns the menu for a specific restaurant.

- Placing Orders

  - `POST /placeorders` - Places a new order.



### Authentication and Authorization

The following endpoints require authentication and/or authorization:

* `POST /restaurants` - Creates a new restaurant (requires authentication).
* `PUT /restaurants/{id}` - Updates a restaurant (requires authentication and authorization).
* `DELETE /restaurants/{id}` - Deletes a restaurant (requires authentication and authorization).
* `POST /menu` - Creates a new menu (requires authentication and authorization).
* `PUT /menu/{id}` - Updates a menu (requires authentication and authorization).
* `DELETE /menu/{id}` - Deletes a menu (requires authentication and authorization).

### Validation and Error Handling

The API validates incoming requests and returns appropriate error messages when necessary. The following error messages may be returned:

* `400 Bad Request` - The request was invalid or malformed.
* `401 Unauthorized` - The request requires authentication but no valid credentials were provided.
* `403 Forbidden` - The request was valid but the user is not authorized to access the requested resource.
* `404 Not Found` - The requested resource could not be found.
* `500 Internal Server Error` - An error occurred on the server.

## Authentication and Authorization

To access sensitive endpoints, users must authenticate using JSON Web Tokens (JWTs). To obtain a JWT, users must first create an account by sending a `POST` request to the `/users` endpoint with their email and password. The API will then return a JWT, which the user can use to authenticate subsequent requests.

## Usage

To test the API, follow these **steps**:

1. Clone the repository to your local machine.
2. Build the project using `mvn clean install`.
3. Run the server using `mvn spring-boot:run`.
4. Use a tool like Postman to send requests to the API endpoints.
5. Authenticate using a JWT obtained from the `/users` endpoint.

## Conclusion


