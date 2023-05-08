package com.wraith.dbs.services;

import com.wraith.dbs.dto.authors.AuthorDTO;
import com.wraith.dbs.dto.authors.AuthorFullDTO;
import com.wraith.dbs.dto.authors.AuthorNamesDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Author;
import com.wraith.dbs.repositories.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements IService<Author, AuthorDTO>
{
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(ModelMapper modelMapper, AuthorRepository authorRepository)
    {
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    public Author findFirstById(String id) { return authorRepository.findFirstById(id); }
    public Author findFirstByNameAndSurname(String name, String surname) { return authorRepository.findByNameEqualsIgnoreCaseAndSurnameEqualsIgnoreCase(name, surname); }

    @Override
    public Author save(Author entity)
    {
        entity.setUpdated_at(OffsetDateTime.now());
        return authorRepository.save(entity);
    }

    @Override
    public void delete(Author entity) { authorRepository.delete(entity); }

    @Override
    public Author convertToEntity(AuthorDTO dto) { return modelMapper.map(dto, Author.class); }

    @Override
    public AuthorFullDTO convertToDTO(Author entity) { return modelMapper.map(entity, AuthorFullDTO.class); }

    public List<AuthorNamesDTO> convertToDTO(ArrayList<Author> entities) { return entities.stream().map(entity -> modelMapper.map(entity, AuthorNamesDTO.class)).collect(Collectors.toList()); }

    public Author findById(String id) { return authorRepository.findById(id).orElse(null); }
}
