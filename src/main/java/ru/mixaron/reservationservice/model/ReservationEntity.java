package ru.mixaron.reservationservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.mixaron.reservationservice.util.Status;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "reservation")
@Data
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "reservation_date")
    private ZonedDateTime reservationDate;

    @Column(name = "create_at")
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private ZonedDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
