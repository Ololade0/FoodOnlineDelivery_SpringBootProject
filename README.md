# Food Delivery API

This is an API for a food delivery service, created using Spring Boot. It provides the necessary endpoints for browsing restaurants, viewing menus, placing orders, and handling order fulfillment.

## Features

The API provides the following features:

* Browsing restaurants
* Viewing restaurant menus
* Placing orders
* Handling order fulfillment
* Authentication and authorization for sensitive endpoints
* Validation and error handling for incoming requests<h2 id="technologies-used">Technologies Used</h2>

<p>The Onine Food Delivery is built using the following technologies:</p>

<ul>
  <li>Java: The backend of the app is developed using Java.</li>
  <li>Spring Boot: The app is built using the Spring Boot framework, which simplifies the development of  applications in Java.</li>
  <li>MongoDB: The app uses MongoDB as the database to store user information and transaction history.</li>



## Endpoints
- SignUp User(PostMapping Request)
* `/api/v1/auth/signup` -Creates a new user.

  - Login User(PostMapping Request)
* `/api/v1/auth/login` - Logging in a user that just registered.


-Register Restuarant((PostMapping Request)
* * `/api/v1/auth/restaurant` - Creates a new restaurant (requires authentication).
  
- Browsing Restaurants(GetMapping Request)
  * * `/api/v1/auth/{userId}/{restaurantId}"` - user browsing and viewing a specific restaurants by restaurantId.
   
- Browsing Restaurants(GetMapping Request)
  * * `"/api/v1/auth/name/{userId}/{restaurantName}"` - user browsing and viewing a specific restaurants by restaurantName.


- Viewing Menus
  - `"/api/v1/auth/menuitem/{restaurantId}/{userId}"` - Returns the menu for a specific restaurant.

- Placing Orders
  - `"/api/v1/auth/placeOrder"` - User placing order in a specific restuarant.



### Authentication and Authorization

The following endpoints require authentication and/or authorization:
* `POST /restaurants` - Creates a new restaurant (requires authentication).
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

  ### Custom Validation and Error Handling
  * `UserCannotBeFoundException` -  This indicate that an operation failed because a specified user could not be found. This could occur when application is trying to look up information for a user account that does not exist
* `MenuItemCannotBeFoundException` -  This indicate that an operation failed because a specified menu item could not be found. This could occur when the application is trying to retrieve information for a menu item that has been deleted or renamed..
* `OrderAlreadyExistException` - This indicate that an operation failed because an order with a specified identifier already exists. This could when the application is trying to create a new order with an identifier that is already in use.
* ` OrderCannotBeFoundException` -  This indicate that an operation failed because an order with a specified identifier or other criteria could not be found. This could occur when the application is trying to retrieve information for an order that has been deleted or has not yet been created.
* `RestaurantCannotBeFound` - This indicate that an operation failed because a specified restaurant could not be found. if the application is trying to retrieve information for a restaurant that has been deleted or has not yet been created.
* * ` UserAlreadyExistException` - This indicate that an operation failed because a user with a specified identifier or other criteria already exists. This could occur when the application is trying to create a new user with an identifier that is already in use.


## Authentication and Authorization

To access sensitive endpoints, users must authenticate using JSON Web Tokens (JWTs). To obtain a JWT, users must first create an account by sending a `POST` request to the `/signup` endpoint with their email and password. The API will then return a JWT, which the user can use to authenticate subsequent requests.

## Usage

To test the API, follow these **steps**:

<p>To run the Bank App on your local machine, follow these steps:</p>

<ol>
  <li>Clone the repository:
    <br><code>git clone https://github.com/Ololade0/FoodOnlineDelivery_SpringBootProject.git</code></li>
  <li>Create a MongoDB database and update the application.properties file with your database credentials.</li>
  <li>Start the application:
    <br><code>mvn clean install</code></li>
    <br><code>mvn spring-boot:run</code></li>
  <li>Access the app in your web browser at http://localhost:8090.</li>
  <br><code>Authenticate using a JWT obtained from the `/signup` endpoint</code></li>
  <br><code>Use a tool like Postman to send requests to the API endpoints.</code></li>
</ol>

## Conclusion


