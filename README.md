#  COMP 301 - EventPlanner Mikroservis Projesi

Bu proje, **COMP 301 – Yazılım Mimarileri ve Araçları** dersi kapsamında geliştirilmiş, mikroservis mimarisine dayanan bir **Etkinlik Planlama Uygulamasıdır (EventPlanner)**.

##  Proje Amacı

Temel amaç, **Spring Boot** ekosistemini kullanarak ölçeklenebilir, esnek ve bağımsız bir şekilde dağıtılabilir mikroservisler tasarlamak, uygulamak ve bu servisleri **Render** bulut platformuna dağıtmaktır.

---

##  Mimari Genel Bakış

Uygulama, temel işlevleri birbirinden ayıran (User, Event, Booking, Payment) dört ana mikroservisten oluşur. Servisler, **Eureka Server** aracılığıyla birbirlerini keşfeder ve **API Gateway** üzerinden dış dünyaya açılır.



###  Temel Teknolojiler

| Kategori | Teknoloji | Açıklama |
| :--- | :--- | :--- |
| **Backend** | Spring Boot 4.0.0 (Java 23) | Servislerin geliştirildiği ana çerçeve. |
| **Mimari Stil** | Mikroservisler, RESTful API | Modüler ve dağıtık yapı. |
| **Veritabanları** | PostgreSQL, (MongoDB önerilir) | İlişkisel ve NoSQL veri depolama için. |
| **Servis Keşfi** | Spring Cloud Netflix Eureka | Servislerin birbirini dinamik olarak bulması. |
| **İletişim** | REST (Senkron), RabbitMQ (Asenkron) | Servisler arası iletişim ve olay tabanlı mesajlaşma. |
| **Dağıtım** | Docker, Render Cloud | Konteynerleştirme ve Buluta dağıtım platformu. |
| **Güvenlik** | Spring Security, JWT | Kimlik doğrulama ve yetkilendirme. |

---

## 📦 Proje Modülleri (Mikroservisler)

Proje, çok modüllü Maven yapısı kullanılarak yönetilmektedir.

| Modül | Sorumluluk | Port | Veritabanı |
| :--- | :--- | :--- | :--- |
| `eureka-server` | Service Discovery merkezini sağlar. | 8761 | Yok |
| `user-service` | Kullanıcı kaydı, girişi, JWT oluşturma ve profil yönetimi. | 8081 | PostgreSQL |
| `event-catalog-service` | Etkinlik listeleme, detaylar ve kategori yönetimi. | 8082 | MongoDB |
| `booking-service` | Bilet rezervasyonu ve müsaitlik kontrolü. | 8083 | PostgreSQL |
| `payment-service` | Ödeme simülasyonu/işlemleri ve işlem geçmişi. | 8084 | PostgreSQL |
| `api-gateway` | Tüm dış istekleri yakalar ve yönlendirir. | 8080 | Yok |

---

## 🚀 Projeyi Yerel Ortamda Çalıştırma

Projeyi yerel olarak ayağa kaldırmak için aşağıdaki adımları takip edin:

### Ön Koşullar

* **Java 23 (JDK)** veya uyumlu bir sürüm
* **Maven 3.x**
* **Docker** ve **Docker Compose** (Veritabanları için)
* **PostgreSQL** (Port 5432'de çalışıyor olmalı)

### Adım 1: Veritabanlarını Başlatma

Öncelikle PostgreSQL ve RabbitMQ konteynerlerini başlatın (Docker Compose kullanılması tavsiye edilir).

### Adım 2: Çekirdek Servisleri Başlatma

1.  **Eureka Server:** İlk olarak `eureka-server` modülünü çalıştırın. (Eureka sunucusu olmadan diğer servisler kaydolamaz.)
    ```bash
    cd eureka-server
    mvn spring-boot:run
    ```
2.  **Config Server (Gerekiyorsa):** Yapılandırma sunucusunu başlatın.

### Adım 3: Mikroservisleri Başlatma

Her bir mikroservis modülünü (örn: `user-service`) ayrı bir terminalde çalıştırın:

```bash
# User Service
cd user-service
mvn spring-boot:run
