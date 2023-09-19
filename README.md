## Lucyd REST API

The Lucyd REST API is a decision engine built in Java using the Spring Boot framework for the Lucyd front-end application. 


## Features

- Use of MySQL database
- Lombok for time saving
- Flyway and migrations for the database management
- Create, read, update, and delete a policy


## API Reference

#### Get all policies

```http
  GET /policies
```

#### Get policy

```http
  GET /policies/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of the policy. |

#### Create policy

```http
  POST /policies
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `String` | **Required**. The name of the policy. |



## Documentation

For a better understanding of the API, see the 
[Swagger Documentation](https://lucyd-api-production.up.railway.app/swagger-ui/index.html).


## Authors

- [@mutheusalmeida](https://www.github.com/mutheusalmeida)


