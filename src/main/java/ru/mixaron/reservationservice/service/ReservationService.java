package ru.mixaron.reservationservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mixaron.reservationservice.dto.ReservationDTO;
import ru.mixaron.reservationservice.exception.ForbiddenActionException;
import ru.mixaron.reservationservice.exception.NotFoundException;
import ru.mixaron.reservationservice.model.ReservationEntity;
import ru.mixaron.reservationservice.repository.ReservationRepository;
import ru.mixaron.reservationservice.util.ReservationMapper;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper mapper;

    public void createReservation(ReservationDTO dto) {
        ReservationEntity reservation = mapper.toEntity(dto);
        reservationRepository.save(reservation);
    }

    public List<ReservationDTO> getAll(long userId) {
        List<ReservationEntity> reservations = reservationRepository.findAllByUserId(userId);

        if (reservations.isEmpty()) {
            throw new NotFoundException("Not found reservations with id " + userId);
        }

        return reservations.stream().map(mapper::toDTO).toList();
    }

    public ReservationDTO getById(long userId, UUID uuid) {
        ReservationEntity entity = reservationRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Reservation not found with ID: " + uuid));

        if (entity.getUserId() != userId) {
            throw new ForbiddenActionException("User " + userId + " does not have permission to access this reservation.");
        }

        return mapper.toDTO(entity);
    }

    public void cancel(long userId, UUID uuid) {
        ReservationEntity entity = reservationRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Reservation not found with ID: " + uuid));

        checkPermission(entity, userId);
        reservationRepository.delete(entity);
    }

    public void update(long userId, ReservationDTO dto) {
        ReservationEntity entity = reservationRepository.findById(dto.getUuid()).
                orElseThrow(() -> new NotFoundException("Not found record"));
        checkPermission(entity, userId);
        updateEntity(entity, dto);
        reservationRepository.save(entity);
    }

    private void updateEntity(ReservationEntity entity, ReservationDTO dto) {
        if (dto.getStatus() != null && !dto.getStatus().equals(entity.getStatus())) {
            entity.setStatus(dto.getStatus());
        }

        if (dto.getReservationDate() != null && !dto.getReservationDate().equals(entity.getReservationDate())) {
            entity.setReservationDate(dto.getReservationDate());
        }
    }

    private void checkPermission(ReservationEntity entity, long userId) {
        if (entity.getUserId() != userId) {
            throw new ForbiddenActionException("User " + userId + " does not have permission to access this reservation.");
        }
    }
    /*
    TODO проверить новые методы все от getById
         Не работает uuid в запросе, не работает метод update впринципе.
     */
}



