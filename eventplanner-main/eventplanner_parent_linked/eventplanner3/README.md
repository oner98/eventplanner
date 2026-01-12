# eventplanner3
Tamam! O zaman `README.md` dosyasını **ayrıntılı, görsel olarak zengin ve profesyonel bir formatta** hazırlayalım. Mikroservis mimarisi, API Gateway ve Eureka Server özelliklerini, örnek istekleri ve proje şemasını içerecek şekilde yapacağız. İşte detaylı versiyon:

```markdown
# API Gateway & Service Discovery Project

Bu proje, **Spring Boot** ile geliştirilmiş bir **API Gateway** ve **Eureka Service Discovery** çözümünü içerir. Mikroservis mimarisi için merkezi bir geçiş noktası ve servis keşfi altyapısı sağlar. Gateway üzerinden dinamik route oluşturulabilir ve servislerin sağlığı merkezi olarak izlenebilir.

---

## İçindekiler

- [Proje Hakkında](#proje-hakkında)  
- [Özellikler](#özellikler)  
- [Mimari](#mimari)  
- [Kurulum](#kurulum)  
- [Yapılandırma](#yapılandırma)  
- [Kullanım](#kullanım)  
- [API Örnekleri](#api-örnekleri)  
- [Katkıda Bulunma](#katkıda-bulunma)  
- [Lisans](#lisans)  

---

## Proje Hakkında

Bu proje, mikroservis tabanlı bir altyapıda aşağıdaki amaçları gerçekleştirir:

1. **Servis Keşfi:** Servislerin merkezi bir noktada kayıt ve keşfini sağlar (Eureka Server).  
2. **API Gateway:** Tüm mikroservis çağrılarını tek noktadan yönlendirir ve dinamik route yönetimi sağlar.  
3. **Health & Info Monitoring:** Yönetim uç noktaları üzerinden servislerin sağlık durumu ve temel bilgileri görüntülenebilir.  

---

## Özellikler

- **Eureka Server:** Mikroservislerin kayıt ve keşfi  
- **Spring Cloud Gateway:** Dinamik route yönetimi ve merkezi API geçiş noktası  
- **Yönetim Endpointleri:** `health` ve `info` ile servis durumu izleme  
- **Servis Keşfi:** Gateway, Eureka üzerinden servisleri otomatik olarak keşfeder  
- **Kolay Konfigürasyon:** `application.properties` üzerinden tüm ayarlar  

---

## Mimari

```

```
       ┌───────────────┐
       │   Client      │
       └───────┬───────┘
               │
               ▼
       ┌───────────────┐
       │ API Gateway   │
       │ (Spring Boot) │
       └───────┬───────┘
               │
```

┌───────────────┴───────────────┐
│                               │
▼                               ▼
┌───────────┐                  ┌───────────┐
│ Service A │                  │ Service B │
└───────────┘                  └───────────┘
▲                               ▲
│                               │
└───────────┐      ┌───────────┘
▼      ▼
┌─────────────┐
│ Eureka Server│
└─────────────┘

````

---

## Kurulum

### Gereksinimler

- Java 17+
- Maven
- IDE (IntelliJ, Eclipse veya VS Code)
- Spring Boot 3.x

### Adım Adım Kurulum

1. Projeyi klonlayın:

```bash
git clone <proje-repo-url>
cd <proje-klasörü>
````

2. Maven bağımlılıklarını yükleyin:

```bash
mvn clean install
```

3. Eureka Server ve API Gateway uygulamalarını çalıştırın:

```bash
mvn spring-boot:run
```

---

## Yapılandırma

`application.properties` dosyasında temel konfigürasyon örneği:

```properties
server.port=8080
spring.application.name=api-gateway

# Eureka bağlantısı
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

# Gateway Discovery üzerinden route üretimi
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true

# Yönetim endpointleri
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
```

> **Not:** Eureka Server ayrı bir Spring Boot uygulaması olarak çalıştırılmalıdır. Genellikle port 8761 kullanılır.

---

## Kullanım

* **Eureka Server:**
  Tüm kayıtlı servisleri `http://localhost:8761` adresinden görüntüleyebilirsiniz.

* **API Gateway:**
  Mikroservislere tek giriş noktası sağlar:

  ```
  http://localhost:8080/{service-id}/{endpoint}
  ```

* **Health Check:**

  ```
  http://localhost:8080/actuator/health
  ```

* **Info Endpoint:**

  ```
  http://localhost:8080/actuator/info
  ```

---

## API Örnekleri

### Service A’ye GET isteği

```bash
GET http://localhost:8080/service-a/api/v1/items
```

### Service B’ye POST isteği

```bash
POST http://localhost:8080/service-b/api/v1/orders
Content-Type: application/json

{
  "productId": 123,
  "quantity": 2
}
```

### Health Check

```bash
GET http://localhost:8080/actuator/health
```

### Info Endpoint

```bash
GET http://localhost:8080/actuator/info
```

---

## Katkıda Bulunma

1. Forklayın
2. Yeni bir branch oluşturun:

   ```bash
   git checkout -b feature/yenilik
   ```
3. Değişikliklerinizi commit edin:

   ```bash
   git commit -m 'Yeni özellik eklendi'
   ```
4. Pushlayın:

   ```bash
   git push origin feature/yenilik
   ```
5. Pull request oluşturun

---

## Lisans

Bu proje MIT Lisansı ile lisanslanmıştır. Daha fazla bilgi için [LICENSE](LICENSE) dosyasına bakabilirsiniz.

```

---

Eğer istersen, ben bunu bir adım daha ileri götürüp **örnek mikroservisler (Service A, Service B) için basit birer kod örneği ve test endpointleri** de ekleyebilirim, böylece proje hem çalışır hem de direkt denenebilir olur.  

Bunu yapmamı ister misin?
```
