package com.wraith.dbs.services;

import com.wraith.dbs.dto.PublicationDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Publication;
import com.wraith.dbs.repositories.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class PublicationService implements IService<Publication, PublicationDTO>
{
    private final ModelMapper modelMapper;
    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(ModelMapper modelMapper, PublicationRepository publicationRepository)
    {
        this.modelMapper = modelMapper;
        this.publicationRepository = publicationRepository;
    }

    public Publication findFirstById(String id) { return publicationRepository.findFirstById(id); }

    @Override
    public Publication save(Publication entity)
    {
        entity.setUpdated_at(OffsetDateTime.now());
        return publicationRepository.save(entity);
    }

    @Override
    public void delete(Publication entity) { publicationRepository.delete(entity); }

    @Override
    public Publication convertToEntity(PublicationDTO dto) { return modelMapper.map(dto, Publication.class); }

    @Override
    public PublicationDTO convertToDTO(Publication entity) { return modelMapper.map(entity, PublicationDTO.class); }
}
