

---

# Event Planner Microservices Project

A hands-on exploration of microservices architecture using Spring Boot and Spring Cloud.

🔗 **GitHub Repository:**  
https://github.com/oner98/eventplanner.git


---

## 📌 Introduction

The **Event Planner Microservices Project** is a practical backend system developed to understand and apply modern microservices architecture concepts using **Spring Boot 3**, **Spring Cloud**, and **Java 17**.

Instead of building a traditional monolithic application, this project was intentionally designed as a **microservices-based system** to gain real-world experience with:
- Service separation
- Service discovery
- Inter-service communication
- Distributed system design

The primary focus of this project is **learning, clarity, and correctness**, rather than unnecessary complexity.

---

## 🧩 Project Overview

### What This Project Does

The Event Planner system is a distributed backend application that manages:

- **Events** – creation and listing of events  
- **Bookings** – reservations made for events  
- **Payments** – payment processing and status tracking  
- **Service Discovery** – dynamic service registration and lookup  

Each responsibility is handled by a **separate microservice**, following the **Single Responsibility Principle**.

---

## 🧱 Core Services

The system consists of **five independent Spring Boot applications**:

### 1. Discovery Server (Eureka)
- Central service registry
- Enables dynamic service discovery
- Eliminates hardcoded service URLs

### 2. Event Service
- Manages event creation and retrieval
- Exposes RESTful endpoints for event operations

### 3. Booking Service
- Handles event reservations
- Stores booking details such as event ID, user ID, and ticket count

### 4. Payment Service
- Manages payment lifecycle
- Tracks payment status using enums

### 5. API Gateway (Passive / Optional)
- Included in the project structure
- Not actively routing traffic yet
- Prepared for future enhancements

---

## 📂 Project Structure

The project is built using a **Maven multi-module architecture**:

```

eventplanner-parent
│
├── discovery-server
├── api-gateway2
├── event-service
├── booking-service
└── payment-service

```

Each module:
- Is a standalone Spring Boot application
- Has its own configuration
- Can run independently
- Shares dependency versions via the parent POM

---

## 🛠 Technology Stack

### Core Technologies
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cloud 2023.0.0**
- **Maven (Multi-module)**
- **Spring Cloud Netflix Eureka**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (development)
- **PostgreSQL** (production-ready)
- **Lombok**

---

## 🏗 Architecture & Design Decisions

### Microservices Principles Applied

- **Service Independence**  
  Each service has its own codebase, configuration, and database.

- **Database per Service**  
  Services do not share databases.

- **Service Discovery**  
  All services register with Eureka and communicate using service names.

- **Loose Coupling**  
  Services interact via REST APIs instead of direct dependencies.

---

## 🔄 Inter-Service Communication

- REST-based communication
- Service discovery via Eureka
- No hardcoded IP addresses or ports

**Examples:**
- Booking Service → Event Service (event validation)
- Payment Service → Booking Service (future-ready)

---

## 🚀 Development Process

### Phase 1 – Parent Project & Discovery Server
- Created `eventplanner-parent` POM
- Centralized:
  - Spring Boot & Spring Cloud versions
  - Shared dependencies
- Implemented Eureka Discovery Server on port **8761**

### Phase 2 – Core Microservices

#### Event Service
- REST endpoints:
  - `POST /events`
  - `GET /events`
  - `GET /events/{id}`
- Layered architecture:
  - Controller
  - Service
  - Repository
  - Entity

#### Booking Service
- Manages reservations
- Stores:
  - `eventId`
  - `userId`
  - `ticketCount`
  - `status`
- Designed for future event validation

#### Payment Service
- Implements `PaymentStatus` enum
- Handles payment lifecycle
- Keeps logic simple but realistic

### Phase 3 – Security
- Spring Security enabled
- HTTP Basic Authentication
- CSRF disabled for API usage
- Prepared for future JWT integration

---

## ⚙ Configuration & Ports

| Service           | Port |
|------------------|------|
| Discovery Server | 8761 |
| Event Service    | 8081 |
| Booking Service  | 8082 |
| Payment Service  | 8083 |
| API Gateway      | 8080 |

---

## 🧪 Testing & Validation

### Manual Testing
- Services tested individually using Postman
- Verified:
  - CRUD operations
  - HTTP status codes
  - Service discovery behavior

### Eureka Dashboard
- Monitored:
  - Active services
  - Health status
  - Registration issues

---

## ⚠ Challenges & Solutions

### Dependency Conflicts
**Solution:**  
Used `dependencyManagement` in the parent POM to control versions centrally.

### Service Communication Issues
**Solution:**  
Replaced hardcoded URLs with Eureka-based service discovery.

### Package & Path Errors
**Solution:**  
Strict alignment of package names with directory structure.

---

## 📘 Lessons Learned

### What Worked Well
- Maven multi-module structure
- Layered service architecture
- Eureka-based discovery
- Starting simple and evolving gradually

### Key Takeaways
- Microservices add complexity, but also clarity
- Architectural decisions matter early
- Distributed systems require patience when debugging

---

## 🎯 Skills Gained

- Microservices architecture
- Spring Boot & Spring Cloud ecosystem
- Service discovery patterns
- RESTful API design
- Maven multi-module projects
- Security basics in distributed systems

---

## 🔮 Future Enhancements

- Activate API Gateway routing
- JWT-based authentication
- Feign Client integration
- Circuit breakers (Resilience4j)
- Centralized logging
- Docker & Kubernetes deployment
- Event-driven communication (RabbitMQ / Kafka)
- User & Notification services

---

## 🧾 Conclusion

The **Event Planner Microservices Project** is a practical and working example of modern backend architecture.

It significantly improved understanding of:
- Distributed systems
- Service discovery
- Microservice boundaries
- Spring ecosystem best practices

The project also provides a strong foundation for scalability and future enhancements.

---

## 👥 Team Members

- **Ali Öner Filibeli**
- **Deren Güray**
- **Ahmet Zavodi**
```

---


