# Order Stream

Comprehensive order processing microservices system built with Spring Boot, PostgreSQL, and RabbitMQ.
Designed with clean architecture and event-driven processing using message queues.

---

## Prerequisites

Make sure you have the following installed:

* Java 21 (LTS)
* Apache Maven 3.9+
* Docker & Docker Compose

---

## Architecture

Architecture of this project can be viewed here 

---

##  Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/order-stream.git
cd order-stream
```

---

### 2. Start Infrastructure (IMPORTANT)

This project depends on services like PostgreSQL and RabbitMQ.

```bash
docker-compose up -d
```

 Ensure all containers are running before proceeding.

---

### 3. Build the Project

```bash
mvn clean install
```

---

### 4. Run the Microservices

####  Product Service

```bash
cd product.service
mvn spring-boot:run
```

---

####  Order Service

```bash
cd order.service
mvn spring-boot:run
```

---

####  GraphQL Gateway

```bash
cd graphql.gateway
mvn spring-boot:run
```

---

##  API Documentation

Swagger UI can be accessed at:

```
http://localhost:8080/swagger-ui/index.html#
```

---

##  Troubleshooting

###  Error: `Failed to load ApplicationContext`

**Cause:**
Database or message broker is not running.

**Fix:**

```bash
docker-compose up -d
```

---

### Error: `release version 21 not supported`

**Cause:**
Incorrect Java version.

**Fix:**

* Install Java 21
* Set `JAVA_HOME` correctly
* Restart terminal

---

### Services not starting properly

**Check:**

* Docker containers are running
* Required ports are free
* View logs using:

```bash
docker-compose logs
```

---

##  Notes

* Always start Docker services before building or running the app
* Run services in separate terminals for better debugging

---

##  Contributing

Hope you'll have a great learning experience from this project. Happy Contributing!
