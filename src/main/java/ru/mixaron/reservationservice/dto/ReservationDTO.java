package ru.mixaron.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mixaron.reservationservice.util.Status;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private UUID uuid;
    private long userId;
    private ZonedDateTime reservationDate;
    private Status status;
}
