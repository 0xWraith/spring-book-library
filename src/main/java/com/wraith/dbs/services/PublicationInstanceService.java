package com.wraith.dbs.services;

import com.wraith.dbs.dto.PublicationInstanceDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.PublicationInstance;
import com.wraith.dbs.repositories.PublicationInstanceRepository;
import com.wraith.dbs.utils.enums.EPublicationInstanceStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@Service
public class PublicationInstanceService implements IService<PublicationInstance, PublicationInstanceDTO>
{
    private final ModelMapper modelMapper;
    private final PublicationInstanceRepository publicationInstanceRepository;

    @Autowired
    public PublicationInstanceService(ModelMapper modelMapper, PublicationInstanceRepository publicationInstanceRepository)
    {
        this.modelMapper = modelMapper;
        this.publicationInstanceRepository = publicationInstanceRepository;
    }

    @Override
    public PublicationInstance save(PublicationInstance entity)
    {
        entity.setUpdated_at(OffsetDateTime.now());
        return publicationInstanceRepository.save(entity);
    }

    @Override
    public void delete(PublicationInstance entity) { publicationInstanceRepository.delete(entity); }

    @Override
    public PublicationInstance convertToEntity(PublicationInstanceDTO dto) { return modelMapper.map(dto, PublicationInstance.class); }

    @Override
    public PublicationInstanceDTO convertToDTO(PublicationInstance entity) { return modelMapper.map(entity, PublicationInstanceDTO.class); }

    public PublicationInstance findAvailablePublicationInstanceForRental(String publicationId, EPublicationInstanceStatus status)
    {
        ArrayList<PublicationInstance> publicationInstances = publicationInstanceRepository.findAvailablePublicationInstanceForRental(publicationId, status);

        if(publicationInstances.size() == 0)
            return null;

        return publicationInstances.get(0);
    }

    public void deletePublicationInstance(String instanceId) { publicationInstanceRepository.deletePublicationInstance(instanceId); }

    public void deleteAllPublicationInstances(String publicationId) { publicationInstanceRepository.deleteAllPublicationInstances(publicationId); }

    public PublicationInstance findFirstById(String instanceId) { return publicationInstanceRepository.findFirstById(instanceId); }
}
