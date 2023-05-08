package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.CategoryDTO;
import com.wraith.dbs.models.Category;
import com.wraith.dbs.models.PublicationCategory;
import com.wraith.dbs.models.PublicationInstance;
import com.wraith.dbs.services.CategoryService;
import com.wraith.dbs.services.PublicationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
public class CategoryController
{

    private final CategoryService categoryService;
    private final PublicationCategoryService publicationCategoryService;

    @Autowired
    public CategoryController(CategoryService categoryService, PublicationCategoryService publicationCategoryService)
    {
        this.categoryService = categoryService;
        this.publicationCategoryService = publicationCategoryService;
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO)
    {

        if (categoryDTO.isDTOInvalid())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Category category = null;

        category = categoryService.findFirstByName(categoryDTO.getName());

        if(category != null)
            return new ResponseEntity<>(category, HttpStatus.CONFLICT);


        category = categoryService.save(categoryService.convertToEntity(categoryDTO));

        return new ResponseEntity<>(categoryService.convertToDTO(category), HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable String categoryId)
    {
        Category category = categoryService.findFirstById(categoryId);

        if(category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(categoryService.convertToDTO(category), HttpStatus.OK);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDTO categoryDTO)
    {

        Category category = categoryService.findFirstById(categoryId);

        if(category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(categoryDTO.isDTOInvalid())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(categoryService.findFirstByName(categoryDTO.getName()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        category.setName(categoryDTO.getName());

        category = categoryService.save(category);

        return new ResponseEntity<>(categoryService.convertToDTO(category), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId)
    {

        Category category = categoryService.findFirstById(categoryId);

        if(category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Collection<PublicationCategory> publicationInstances = category.getPublicationCategoryCollection();

        publicationInstances.forEach(publicationCategoryService::delete);

        categoryService.delete(category);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
