<h1 align="center">
    <img src="https://readme-typing-svg.herokuapp.com/?font=Righteous&size=35&center=true&vCenter=true&width=900&height=70&duration=4000&lines=Crop+Monitoring+System+-+Green+Shadow+(Pvt)+Ltd&color=bcfab5" />
</h1>


### Description
This is a backend implementation of a Crop Monitoring Management System using the Spring Framework and other Java-based technologies. The API provides endpoints to manage crops, fields, equipment, staff, vehicles, and monitoring logs. The project is built using Spring Boot for RESTful services, Hibernate for database interaction, and MySQL as the database. Lombok is used to reduce boilerplate code

### Technologies Used

- Spring Framework (Web MVC): Backend framework for building RESTful services.
- Hibernate: ORM (Object Relational Mapping) for database interaction.
- Spring Data JPA: Data repository abstraction layer for database operations.
- MySQL: Relational database used for storing POS data.
- Lombok: Used to reduce boilerplate code (getters/setters, constructors) and implement logging using SLF4J.
- SLF4J: Simple Logging Facade for Java used for logging and debugging purposes.

### API Endpoints

#### Field Management
- **GET** `/fields`          : Get all fields.
- **GET** `/fields/{id}`     : Get a field by ID.
- **POST** `/fields`         : Add a new field.
- **PUT** `/fields/{id}`     : Update a field by ID.
- **DELETE** `/fields/{id}`  : Delete a field by ID.

#### Crop Management
- **GET** `/crops`           : Get all crops.
- **GET** `/crops/{id}`      : Get a crop by ID.
- **POST** `/crops`          : Add a new crop.
- **PUT** `/crops/{id}`      : Update a crop by ID.
- **DELETE** `/crops/{id}`   : Delete a crop by ID.

#### Staff Management
- **GET** `/staff`           : Get all staff.
- **GET** `/staff/{id}`      : Get a staff member by ID.
- **POST** `/staff`          : Add a new staff member.
- **PUT** `/staff/{id}`      : Update a staff member by ID.
- **DELETE** `/staff/{id}`   : Delete a staff member by ID.

#### Monitoring Log Management
- **GET** `/monitoring-logs`          : Get all monitoring logs.
- **GET** `/monitoring-logs/{id}`     : Get a monitoring log by ID.
- **POST** `/monitoring-logs`         : Add a new monitoring log.
- **PUT** `/monitoring-logs/{id}`     : Update a monitoring log by ID.
- **DELETE** `/monitoring-logs/{id}`  : Delete a monitoring log by ID.

#### Vehicle Management
- **GET** `/vehicles`         : Get all vehicles.
- **GET** `/vehicles/{id}`    : Get a vehicle by ID.
- **POST** `/vehicles`        : Add a new vehicle.
- **PUT** `/vehicles/{id}`    : Update a vehicle by ID.
- **DELETE** `/vehicles/{id}` : Delete a vehicle by ID.

#### Equipment Management
- **GET** `/equipment`         : Get all equipment.
- **GET** `/equipment/{id}`    : Get equipment by ID.
- **POST** `/equipment`        : Add new equipment.
- **PUT** `/equipment/{id}`    : Update equipment by ID.
- **DELETE** `/equipment/{id}` : Delete equipment by ID.


### API Documentation

You can view the detailed API documentation with example requests and responses [here](https://documenter.getpostman.com/view/35385634/2sAYBa8pDu).

### Clone Repository
<ul>
  <li>Clone the repository:
    <br>git clone https://github.com/sachiniwathudura/CropMonitoringSystem-BackEnd.git
  </li>
  <li>Configure the Database:
    <br>Set up your MySQL database.
  </li>
  <li>Update Hibernate Configuration:
    <br>Update and configure the Hibernate settings for MySQL. Ensure the correct JDBC URL, username, and password are set for your MySQL database.
  </li>
  <li>Update Logger Configuration:
    <br>Update the logger configuration for the application in logback.xml.
  </li>
  <li>Build & Deploy:
    <br>Build the project using Maven.
    <br>Run the Spring application.
  </li>
</ul>

### License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

 Happy Coding :)
