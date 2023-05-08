package com.wraith.dbs.services;

import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Author;
import com.wraith.dbs.models.PublicationAuthor;
import com.wraith.dbs.repositories.PublicationAuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class PublicationAuthorService implements IService<PublicationAuthor, PublicationAuthor>
{

    private final ModelMapper modelMapper;
    private final PublicationAuthorRepository publicationAuthorRepository;


    @Autowired
    public PublicationAuthorService(ModelMapper modelMapper, PublicationAuthorRepository publicationAuthorRepository)
    {
        this.modelMapper = modelMapper;
        this.publicationAuthorRepository = publicationAuthorRepository;
    }

    public ArrayList<Author> findAllPublicationAuthors(String id) { return publicationAuthorRepository.findAllPublicationAuthors(id); }

    @Override
    public PublicationAuthor save(PublicationAuthor entity) { return publicationAuthorRepository.save(entity); }

    @Override
    public void delete(PublicationAuthor entity) { publicationAuthorRepository.delete(entity); }

    @Override
    public PublicationAuthor convertToEntity(PublicationAuthor dto) { return modelMapper.map(dto, PublicationAuthor.class); }

    @Override
    public PublicationAuthor convertToDTO(PublicationAuthor entity) { return modelMapper.map(entity, PublicationAuthor.class); }

    public void deleteAllPublicationAuthors(String publicationId) { publicationAuthorRepository.deleteAllPublicationAuthors(publicationId); }
}
