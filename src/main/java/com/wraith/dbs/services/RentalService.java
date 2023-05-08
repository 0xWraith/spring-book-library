package com.wraith.dbs.services;

import com.wraith.dbs.dto.rental.RentalFullDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Rental;
import com.wraith.dbs.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService implements IService<Rental, RentalFullDTO>
{

    private final ModelMapper modelMapper;
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(ModelMapper modelMapper, RentalRepository rentalRepository)
    {
        this.modelMapper = modelMapper;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental save(Rental entity) { return rentalRepository.save(entity); }

    @Override
    public void delete(Rental entity) { rentalRepository.delete(entity); }

    @Override
    public Rental convertToEntity(RentalFullDTO dto) { return modelMapper.map(dto, Rental.class); }

    @Override
    public RentalFullDTO convertToDTO(Rental entity) { return modelMapper.map(entity, RentalFullDTO.class); }

    public Rental findFirstById(String rentalId) { return rentalRepository.findFirstById(rentalId); }
}
