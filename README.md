# README — EventPlanner Microservices Project (COMP 301, Fall 2025)

**Proje Başlığı:** EventPlanner — Microservices-based Web Application built with Spring Boot

**Kurs:** Software Architectures and Tools (COMP 301)

**Dönem:** Fall 2025

**Hazırlayan:** [Takım Adı / Öğrenciler]

---

## İçindekiler

1. Proje Özeti
2. Hedefler ve Öğrenme Çıktıları
3. Sistem Mimarisi (Yüksek seviye)
4. Spring Initializr — Her microservice için önerilen ayarlar (adım adım)
5. Tüm bağımlılıklar (service bazlı) ve nedenleri
6. Detaylı proje yapısı — paket & dosya düzeni
7. Konfigürasyon örnekleri (`application.yml`/`application.properties`)
8. REST API endpoint örnekleri (her servis için)
9. Güvenlik: JWT akışı ve rol tabanlı yetkilendirme
10. Service discovery & configuration (Eureka + Config Server)
11. Veri tabanı önerileri ve migration stratejileri
12. Test stratejisi: unit, integration, end-to-end
13. Containerization & Deployment (Render için adımlar + Docker örnekleri)
14. Monitoring, logging ve health checks
15. CI/CD önerisi (GitHub Actions örneği)
16. Teslim paketinde bulunması gerekenler
17. Geliştirme notları ve en iyi uygulamalar
18. Katkı, lisans ve iletişim

---

## 1) Proje Özeti

EventPlanner, kullanıcıların etkinlikleri görüntüleyebildiği, etkinlik düzenleyebildiği, rezervasyon yapabildiği ve ödemelerini gerçekleştirebildiği dağıtık mimariye dayalı bir web uygulamasıdır. Proje, microservices mimarisi, servis keşfi, merkezi konfigürasyon, güvenli iletişim ve bulut dağıtımı gibi modern yazılım mimarisi kavramlarını öğretmeyi amaçlar.

Ana microservice'ler:

* **Event Catalog Service** — Etkinliklerin CRUD işlemleri, filtreleme, kategori ve arama.
* **User Service** — Kullanıcı kayıt, giriş (authentication), profil yönetimi, roller (ROLE_USER, ROLE_ADMIN).
* **Booking Service** — Rezervasyon işlemleri, koltuk/kapasite kontrolü, iptal.
* **Payment Service** — Ödeme işlemleri (simülasyon veya ödeme sağlayıcı entegrasyonu) ve işlem kaydı.
* **Eureka Service** — Servis keşfi ve kayıt.
* **Config Server** — Merkezi konfigürasyon yönetimi.

Her servis bağımsız dağıtılabilir ve ölçeklenebilir olmalıdır. Proje Render üzerinde deploy edilecektir.

---

## 2) Hedefler ve Öğrenme Çıktıları

* Microservices tasarım ilkelerini uygulamak (Loose coupling, Single Responsibility).
* Spring Boot ve Spring Cloud bileşenlerini kullanarak servis keşfi, merkezi konfigürasyon ve güvenli iletişim sağlamak.
* RESTful API tasarımı ve doğrulama konularında deneyim kazanmak.
* Unit, integration ve end-to-end testler yazmak.
* Docker kullanımı ve Render platformuna dağıtım.
* Monitoring, logging ve health check uygulamaları eklemek.

---

## 3) Sistem Mimarisi (Yüksek seviye)

Kısa açıklama:

* Kullanıcı istekleri (UI veya API client) Gateway/Load Balancer aracılığıyla ilgili servise gider.
* Servisler birbirleriyle REST üzerinden haberleşir. Kritik yerlerde JWT ile güvenli iletişim sağlanır.
* Eureka Service: Servislerin keşif ve kayıt merkezi.
* Config Server: Tüm servisler için ortak konfigürasyon kaynağı (Git tabanlı veya dosya tabanlı).
* Database: Her servis kendi DB’sine sahip olabilir (polyglot persistence). Örneğin:

  * User Service — PostgreSQL
  * Event Catalog — MongoDB (document-friendly)
  * Booking, Payment — PostgreSQL veya MySQL

Mimari diyagramı (basit metin hali):

```
[Client] --> [Load Balancer] --> [EventCatalog, User, Booking, Payment] <---> [Databases]
                               \--> [Eureka] (Service Registry)
                               \--> [Config Server]
```

---

## 4) Spring Initializr — Her microservice için önerilen ayarlar

> Not: Aşağıdaki adımları `https://start.spring.io/` üzerinde her servis için tekrarlayın. Her servis için ayrı bir proje oluşturun (artifact farklı olmalı: `event-catalog-service`, `user-service`, vb.).

**Ortak ayarlar (her servis için):**

* **Project:** Maven Project
* **Language:** Java
* **Spring Boot:** 3.2.x (veya kurumsal olarak hangi stabil sürüm verilmişse) —  Java 23, değilse Java 17 önerilir. (Render ve Docker base image uyumluluğunu kontrol edin.)
* **Group:** `edu.youruniv.eventplanner` (veya `com.example.eventplanner`)
* **Artifact:** `event-catalog-service` / `user-service` / `booking-service` / `payment-service` / `eureka-server` / `config-server`
* **Name / Description:** uygun şekilde doldurun
* **Packaging:** Jar
* **Java:** 21 (veya proje gereksinimine göre 23)

**Dependencies (service bazlı öneri — tümünü Spring Initializr'dan seçebilirsiniz):**

1. **Event Catalog Service**

   * Spring Web
   * Spring Data MongoDB (veya Spring Data JPA + uygun SQL DB seçilirse)
   * Spring Boot DevTools
   * Spring Boot Actuator
   * Spring Boot Starter Validation
   * Spring Security (opsiyonel, auth kontrolü varsa)
   * springdoc-openapi-starter-webmvc-ui (Swagger)

2. **User Service**

   * Spring Web
   * Spring Data JPA
   * PostgreSQL Driver (pom içinde ekleyin)
   * Spring Security
   * JWT (kütüphane: `jjwt` veya `spring-security-oauth2-jose` ile JWT doğrulama)
   * Spring Boot DevTools
   * Spring Boot Actuator
   * springdoc-openapi-starter-webmvc-ui

3. **Booking Service**

   * Spring Web
   * Spring Data JPA
   * PostgreSQL/MySQL Driver
   * Spring Boot DevTools
   * Spring Boot Actuator
   * springdoc-openapi-starter-webmvc-ui
   * RabbitMQ / Kafka (opsiyonel, asenkron işlemler için) —
  
    

4. **Payment Service**

   * Spring Web
   * Spring Data JPA (transaction history için)
   * PostgreSQL/MySQL Driver
   * Spring Boot DevTools
   * Spring Boot Actuator
   * springdoc-openapi-starter-webmvc-ui
   * Payment gateway SDK (opsiyonel — simülasyon yapacaksanız gerekmez)

5. **Eureka Server**

   * Spring Cloud Netflix Eureka Server
   * Spring Boot Actuator

6. **Config Server**

   * Spring Cloud Config Server
   * Spring Boot Actuator

7. **Shared / Common library (opsiyonel)**

   * common model/DTO/exception sınıfları için ayrı maven module veya JAR

---

## 5) Tüm bağımlılıklar (service bazlı, nedenleriyle)

Aşağıda her bağımlılık için kısa açıklama:

* `spring-boot-starter-web`: REST endpointleri ve web sunucusu için.
* `spring-boot-starter-data-jpa`: SQL DB erişimi için JPA/Hibernate.
* `spring-boot-starter-data-mongodb`: MongoDB için.
* `spring-boot-starter-security`: Authentication ve Authorization.
* `spring-boot-starter-actuator`: Sağlık ve metric endpointleri.
* `spring-boot-devtools`: Geliştirme deneyimi için otomatik reload.
* `spring-boot-starter-validation`: Bean validation (`@Valid`) için.
* `org.springdoc:springdoc-openapi-starter-webmvc-ui`: Swagger/OpenAPI UI.
* `org.projectlombok:lombok`: Boilerplate azaltma.
* `spring-cloud-starter-netflix-eureka-server` / `eureka-client`: Servis keşfi.
* `spring-cloud-config-server` / `spring-cloud-config-client`: Merkezî konfigürasyon.
* `jjwt` veya `spring-security-oauth2-jose`: JWT encode/decode.
* `postgresql` / `mysql-connector-java` / `mongodb-driver-sync`: DB driverları.
* `spring-boot-starter-test`: JUnit, Mockito, test araçları.

> Her bağımlılığı `pom.xml` ya da `build.gradle` içine uygun scope ile ekleyin. Spring Initializr çoğunu otomatik sağlar; restini manuel ekleyin.

---

## 6) Detaylı proje yapısı — örnek (event-catalog-service için)

```
event-catalog-service/
├── .gitignore
├── pom.xml                                
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/youruniv/eventplanner/eventcatalog/
│   │   │        ├── EventCatalogApplication.java     
│   │   │        ├── controller/
│   │   │        │     └── EventController.java      
│   │   │        ├── service/
│   │   │        │     ├── EventService.java           
│   │   │        │     └── EventServiceImpl.java       
│   │   │        ├── repository/
│   │   │        │     └── EventRepository.java       
│   │   │        ├── dto/
│   │   │        │     ├── EventRequest.java          
│   │   │        │     └── EventResponse.java          
│   │   │        ├── model/
│   │   │        │     └── Event.java                  
│   │   │        └── config/
│   │   │             └── OpenApiConfig.java          
│   │   └── resources/
│   │        ├── application.yml            
│   │        └── data/
│   │             └── seed_data.json 
│
│   └── test/
│       └── java/
│           └── edu/youruniv/eventplanner/eventcatalog/
│               ├── controller/
│               │     └── EventControllerTest.java  
│               └── service/
│                     └── EventServiceTest.java


Diğer servisler için de benzer katmanlı bir yapı önerilir: controller, service, repository, dto, model, config.

---

## 7) Konfigürasyon örnekleri

### `application.yml` — Event Catalog (örnek)

```yaml
spring:
  application:
    name: event-catalog-service
  data:
    mongodb:
      uri: ${MONGODB_URI:mongodb://localhost:27017/eventdb}

server:
  port: ${PORT:8081}

eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_SERVER_URL:http://localhost:8761/eureka/}

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

### `application.yml` — User Service (örnek)

```yaml
spring:
  application:
    name: user-service
  datasource:
    url: jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/usersdb
    username: ${DB_USER:postgres}
    password: ${DB_PASS:password}
  jpa:
    hibernate:
      ddl-auto: update

server:
  port: ${PORT:8082}

eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_SERVER_URL:http://localhost:8761/eureka/}

security:
  jwt:
    secret: ${JWT_SECRET:change-me-in-prod}
    expiration-ms: 3600000

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

> Config Server kullanıyorsanız `bootstrap.yml`/`bootstrap.properties` ile merkezi konfigürasyonu işaretleyin.

---

## 8) REST API endpoint örnekleri (her servis için kısa)

### Event Catalog Service

* `GET /api/v1/events` — Tüm etkinlikleri listele (filtre query parametreleri: category, dateFrom, dateTo)
* `GET /api/v1/events/{id}` — Tek etkinlik
* `POST /api/v1/events` — Yeni etkinlik ekle (ADMIN)
* `PUT /api/v1/events/{id}` — Etkinlik güncelle (ADMIN)
* `DELETE /api/v1/events/{id}` — Etkinlik sil (ADMIN)

### User Service

* `POST /api/v1/auth/register` — Kayıt
* `POST /api/v1/auth/login` — Giriş (dönen: access token JWT)
* `GET /api/v1/users/{id}` — Profil (ROLE_USER ya da ROLE_ADMIN)
* `PUT /api/v1/users/{id}` — Profil güncelle

### Booking Service

* `POST /api/v1/bookings` — Rezervasyon oluştur (token ile kullanıcı doğrulama)
* `GET /api/v1/bookings/user/{userId}` — Kullanıcı rezervasyonları
* `POST /api/v1/bookings/{id}/cancel` — Rezervasyon iptal

### Payment Service

* `POST /api/v1/payments` — Ödeme başlat (bookingId, amount, paymentMethod)
* `GET /api/v1/payments/{id}` — İşlem geçmişi

---

## 9) Güvenlik: JWT akışı (özet)

1. Kullanıcı `POST /auth/login` ile kimlik bilgilerini gönderir.
2. User Service kimlik bilgilerini doğrular, başarılı ise bir JWT (access token) üretir ve döner.
3. Client token'ı saklar (ör. browser: localStorage; mobile: secure storage).
4. Diğer servisler (Booking, Payment) gelen isteklerde `Authorization: Bearer <token>` header'ını kontrol eder.
5. Token doğrulaması User Service tarafından veya shared library ile yapılır (public key veya shared secret).

**Rol tabanlı kontroller:** JWT içinde `roles` claim'i taşıyın; `@PreAuthorize("hasRole('ADMIN')")` gibi anotasyonlarla method güvenliği sağlayın.

---

## 10) Service discovery & configuration

* **Eureka Server**: Tüm servisler `eureka-client` olarak kaydolur. Servisler arası çağrı için DNS benzeri isimler kullanılabilir (`http://event-catalog-service/api/v1/...`).
* **Config Server**: Git tabanlı konfigürasyon repo'su kullanın. Her servis için `application-{profile}.yml` dosyaları burada tutulur.

> Not: Spring Cloud sürümlerinin Spring Boot sürümüyle uyumlu olmasına dikkat edin. Spring Cloud BOM (Bill of Materials) kullanımı tavsiye edilir.

---

## 11) Veri tabanı önerileri ve migration

* **Event Catalog:** MongoDB uygundur (esnek etkinlik şeması).
* **User / Booking / Payment:** RDBMS (PostgreSQL) uygundur.
* **Migration:** Flyway veya Liquibase kullanın. Migration scriptleri `src/main/resources/db/migration` içinde versiyonlanmış olmalı.

---

## 12) Test stratejisi (detaylı)

1. **Unit Tests**: JUnit 5 + Mockito. Service katmanı mocklanan repository ile test edilir.
2. **Component / Integration Tests**: `@SpringBootTest` veya `@WebMvcTest` + `Testcontainers` kullanarak gerçek DB (Postgres, Mongo) konteynerleri ile test edin.
3. **Contract Tests**: Eğer servisler birbirine bağlıysa, `PACT` benzeri contract testing kullanımı değerlendirilebilir.
4. **End-to-End Tests**: Tam yığın testler (ör. Playwright/Selenium + API çağrıları) — Render üzerinde deploy edilmiş uygulamaya karşı.
5. **Test Data**: Testlerde deterministik olmak için seed verisi ve teardown mantığı kullanın.

---

## 13) Containerization & Deployment

### Dockerfile (örnek, multi-stage önerilir)

```dockerfile
# Build stage
FROM maven:3.8.8-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Run stage
FROM eclipse-temurin:17-jre
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
```

### Render deployment (özet)

1. Her servis için ayrı bir Render Web Service oluşturun.
2. Repository’yi GitHub’a push edin (her servis ayrı repo veya mono-repo tercihine göre branch/module ayrımı).
3. Render üzerinde build komutu: `mvn -DskipTests package` ve start komutu: `java -jar target/*.jar`.
4. Çevresel değişkenleri (DB URL, JWT_SECRET, EUREKA_SERVER_URL vb.) Render dashboard’dan ayarlayın.

> Her servis kendi public URL'sine sahip olacak; final sunumda bu URL’leri teslim edin.

---

## 14) Monitoring, logging ve health checks

* **Actuator**: `/actuator/health`, `/actuator/metrics` expose edin (prod'da dikkatli filtreleyin).
* **Logging**: Centralized logging için ELK/EFK veya Render logs + external log service kullanılabilir.
* **Distributed Tracing**: Zipkin veya OpenTelemetry ile request tracing ekleyin (opsiyonel ama tavsiye edilir).

---

## 15) CI/CD önerisi (GitHub Actions örneği)

Basit workflow:

* `build.yml`:

  * Trigger: push/PR
  * Steps: checkout, set up JDK 17, cache maven, mvn test, mvn package
  * On success: optionally push Docker image to Docker Hub / GitHub Packages

Render otomatik deploy kullanıyorsanız, push sonrası Render repo trigger ile deploy olabilir.

---

## 16) Teslim paketi (ne teslim edilmeli)

1. Her microservice için kaynak kod (GitHub reposu linkleri)
2. System Design Document (mimari diyagram, API spesifikasyonları, DB şemaları)
3. Test raporu (unit, integration, e2e — ekran görüntüleri, coverage)
4. Deployment Guide (Render için adımlar ve environment variable listesi)
5. Public URL’ler (Render üzerinde her servis için)
6. Sunum dosyası (slides) ve demo planı

---

## 17) Geliştirme notları ve en iyi uygulamalar

* **Single Responsibility**: Her microservice tek bir iş sorumluluğuna odaklanmalı.
* **API Versioning**: `api/v1` şeklinde versiyonlama yapın.
* **Idempotency**: Ödeme/rezervasyon endpoint’leri için idempotency (özellikle ödeme tarafında) düşünün.
* **Circuit Breaker**: Hystrix yerine Resilience4j kullanımı tavsiye edilir (circuit breaker, retry, rate limit).
* **Timeout & Retry Policies**: RestTemplate/WebClient üzerinden çağrı yaparken timeout ayarları belirleyin.
* **Secrets Management**: JWT secret, DB password gibi gizli verileri ortam değişkeni veya secret manager ile yönetin.
* **Dokümantasyon**: OpenAPI spec her servis için sağlanmalı.

---

## 18) Katkı, lisans ve iletişim

* **Contributing:** Fork → feature branch → PR → review → merge
* **Code Style:** Java 17+, clean code, meaningful commit mesajları
* **License:** MIT (veya kurumun istediği lisans)
* **Contact:** [Takım Lideri Email / GitHub handles]

---

## Ek: Hızlı Başlangıç (Local development - 5 dakikalık yol haritası)

1. Her servis için Spring Initializr ile proje oluşturun veya repo’yu clone edin.
2. Local DB’leri ayağa kaldırın (Docker Compose önerilir): Postgres, Mongo.
3. Eureka ve Config Server’ı çalıştırın.
4. User Service ile register/login test edin, JWT token alın.
5. Event Catalog’a event ekleyin.
6. Booking oluşturun (Booking Service), ardından Payment ile ödeme simülasyonu çalıştırın.

---


