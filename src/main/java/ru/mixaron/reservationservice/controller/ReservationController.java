package ru.mixaron.reservationservice.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.reservationservice.dto.ReservationDTO;
import ru.mixaron.reservationservice.service.ReservationService;
import ru.mixaron.reservationservice.util.UUIDRequestDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestHeader("id") long id, @RequestBody ReservationDTO dto) {
        dto.setUserId(id);
        reservationService.createReservation(dto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<List<ReservationDTO>> getAllByUser(@RequestHeader("id") long userId) {
        return ResponseEntity.ok().body(reservationService.getAll(userId));
    }

    @PostMapping("/getById")
    public ResponseEntity<ReservationDTO> getRecordById(@RequestHeader("id") long userId, @RequestBody UUIDRequestDTO uuid) {
        return ResponseEntity.ok().body(reservationService.getById(userId, uuid.getUuid()));
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<HttpStatus> cancelReservation(@RequestHeader("id") long userId, @RequestBody UUIDRequestDTO uuid) {
        reservationService.cancel(userId, uuid.getUuid());
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> updateReservation(@RequestHeader("id") long userId, @RequestBody ReservationDTO dto) {
        reservationService.update(userId, dto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
    /*
    todo update проверить
     */
}
