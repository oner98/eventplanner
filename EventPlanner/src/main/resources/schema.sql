-- USERS
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(30) NOT NULL
);

-- EVENTS
CREATE TABLE events (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        description TEXT,
                        event_date TIMESTAMP NOT NULL
);

-- BOOKINGS
CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          event_id BIGINT NOT NULL,
                          booking_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(30) NOT NULL,

                          CONSTRAINT fk_booking_user
                              FOREIGN KEY (user_id) REFERENCES users(id),

                          CONSTRAINT fk_booking_event
                              FOREIGN KEY (event_id) REFERENCES events(id)
);

-- PAYMENTS
CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          booking_id BIGINT NOT NULL,
                          amount NUMERIC(10,2) NOT NULL,
                          status VARCHAR(30) NOT NULL,
                          payment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_payment_booking
                              FOREIGN KEY (booking_id) REFERENCES bookings(id)
);
