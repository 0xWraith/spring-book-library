package com.wraith.dbs.services;

import com.wraith.dbs.dto.CategoryDTO;
import com.wraith.dbs.dto.authors.AuthorNamesDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Author;
import com.wraith.dbs.models.Category;
import com.wraith.dbs.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements IService<Category, CategoryDTO>
{
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository)
    {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public Category findFirstById(String id) { return categoryRepository.findFirstById(id); }
    public Category findFirstByName(String name) { return categoryRepository.findFirstByNameEqualsIgnoreCase(name); }

    @Override
    public Category save(Category entity)
    {
        entity.setUpdated_at(OffsetDateTime.now());
        return categoryRepository.save(entity);
    }

    @Override
    public void delete(Category entity) { categoryRepository.delete(entity); }

    @Override
    public Category convertToEntity(CategoryDTO dto) { return modelMapper.map(dto, Category.class); }

    @Override
    public CategoryDTO convertToDTO(Category entity) { return modelMapper.map(entity, CategoryDTO.class); }

    public List<String> convertToDTO(ArrayList<Category> entities) { return entities.stream().map(Category::getName).collect(Collectors.toList()); }
}
