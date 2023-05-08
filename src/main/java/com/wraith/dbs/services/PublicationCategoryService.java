package com.wraith.dbs.services;

import com.wraith.dbs.dto.PublicationCategoryDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Category;
import com.wraith.dbs.models.PublicationCategory;
import com.wraith.dbs.repositories.PublicationCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PublicationCategoryService implements IService<PublicationCategory, PublicationCategoryDTO>
{
    private final ModelMapper modelMapper;
    private final PublicationCategoryRepository publicationCategoryRepository;

    @Autowired
    public PublicationCategoryService(ModelMapper modelMapper, PublicationCategoryRepository publicationCategoryRepository)
    {
        this.modelMapper = modelMapper;
        this.publicationCategoryRepository = publicationCategoryRepository;
    }

    public ArrayList<PublicationCategory> findAllByPublicationId(String id) { return publicationCategoryRepository.findAllByPublicationId(id); }

    @Override
    public PublicationCategory save(PublicationCategory entity) { return publicationCategoryRepository.save(entity); }

    @Override
    public void delete(PublicationCategory entity) { publicationCategoryRepository.delete(entity); }

    @Override
    public PublicationCategory convertToEntity(PublicationCategoryDTO dto) { return modelMapper.map(dto, PublicationCategory.class); }

    @Override
    public PublicationCategoryDTO convertToDTO(PublicationCategory entity) { return modelMapper.map(entity, PublicationCategoryDTO.class); }

    public ArrayList<Category> findAllPublicationCategories(String publicationId) { return publicationCategoryRepository.findAllPublicationCategories(publicationId); }

    public void deleteAllPublicationCategories(String publicationId) { publicationCategoryRepository.deleteAllPublicationCategories(publicationId); }
}
