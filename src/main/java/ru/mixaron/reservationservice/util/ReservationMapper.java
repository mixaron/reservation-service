package ru.mixaron.reservationservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.mixaron.reservationservice.dto.ReservationDTO;
import ru.mixaron.reservationservice.model.ReservationEntity;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    // Метод для преобразования из сущности в DTO
    @Mapping(target = "reservationDate", source = "reservationDate")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "uuid", source = "uuid")
    ReservationDTO toDTO(ReservationEntity entity);

    // Метод для преобразования из DTO в сущность
    @Mapping(target = "reservationDate", source = "reservationDate")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ReservationEntity toEntity(ReservationDTO dto);
}
