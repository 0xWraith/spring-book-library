package com.wraith.dbs.services;

import com.wraith.dbs.dto.ReservationDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Reservation;
import com.wraith.dbs.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ReservationService implements IService<Reservation, ReservationDTO>
{
    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ModelMapper modelMapper, ReservationRepository reservationRepository)
    {
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation)
    {

        if (reservation.getCreated_at() == null)
            reservation.setCreated_at(OffsetDateTime.now());

        return reservationRepository.save(reservation);
    }
    @Override
    public void delete(Reservation reservation) { reservationRepository.delete(reservation); }

    @Override
    public Reservation convertToEntity(ReservationDTO dto) { return modelMapper.map(dto, Reservation.class); }

    @Override
    public ReservationDTO convertToDTO(Reservation entity) { return modelMapper.map(entity, ReservationDTO.class); }

    public Reservation findFirstById(String reservationId) { return reservationRepository.findFirstById(reservationId); }
    public Reservation findFirstByPublicationId(String publicationId) { return reservationRepository.findFirstByPublicationId(publicationId); }

}
