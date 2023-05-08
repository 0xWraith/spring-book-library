package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.authors.AuthorFullDTO;
import com.wraith.dbs.dto.PublicationDTO;
import com.wraith.dbs.dto.authors.AuthorNamesDTO;
import com.wraith.dbs.models.*;
import com.wraith.dbs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/publications")
public class PublicationController
{
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublicationService publicationService;
    private final PublicationAuthorService publicationAuthorService;
    private final PublicationCategoryService publicationCategoryService;
    private final PublicationInstanceService publicationInstanceService;

    @Autowired
    public PublicationController(
            AuthorService authorService, CategoryService categoryService,
            PublicationService publicationService, PublicationAuthorService publicationAuthorService,
            PublicationCategoryService publicationCategoryService, PublicationInstanceService publicationInstanceService)
    {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publicationService = publicationService;
        this.publicationAuthorService = publicationAuthorService;
        this.publicationCategoryService = publicationCategoryService;
        this.publicationInstanceService = publicationInstanceService;
    }

    @PostMapping("")
    public ResponseEntity<?> createPublication(@RequestBody PublicationDTO publicationDTO)
    {

        if (publicationDTO.isDTOInvalid())
            return ResponseEntity.badRequest().build();

        if(publicationDTO.getId() != null && publicationService.findFirstById(publicationDTO.getId()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        ArrayList<AuthorNamesDTO> authors = publicationDTO.getAuthors();

        for(AuthorNamesDTO author : authors)
        {
            if(authorService.findFirstByNameAndSurname(author.getName(), author.getSurname()) == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ArrayList<String> categories = publicationDTO.getCategories();

        for(String category : categories)
        {
            if(categoryService.findFirstByName(category) == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String id;
        Publication publication = publicationService.save(publicationService.convertToEntity(publicationDTO));


        id = publication.getId();

        for (AuthorNamesDTO author : authors)
        {
            PublicationAuthor publicationAuthor = new PublicationAuthor();
            Author authorEntity = authorService.findFirstByNameAndSurname(author.getName(), author.getSurname());

            publicationAuthor.setPublication_id(id);
            publicationAuthor.setAuthor_id(authorEntity.getId());

            publicationAuthorService.save(publicationAuthor);
        }

        for (String category : categories)
        {
            PublicationCategory publicationCategory = new PublicationCategory();
            Category categoryEntity = categoryService.findFirstByName(category);

            publicationCategory.setPublication_id(id);
            publicationCategory.setCategory_id(categoryEntity.getId());

            publicationCategoryService.save(publicationCategory);
        }

        publicationDTO.setCreated_at(publication.getCreated_at());
        publicationDTO.setUpdated_at(publication.getUpdated_at());

        return new ResponseEntity<>(publicationDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{publicationId}")
    public ResponseEntity<?> getPublication(@PathVariable String publicationId)
    {
        Publication publication = publicationService.findFirstById(publicationId);

        if(publication == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ArrayList<Author> authors = publicationAuthorService.findAllPublicationAuthors(publicationId);
        ArrayList<Category> categories = publicationCategoryService.findAllPublicationCategories(publicationId);

        publication.setAuthors((ArrayList<AuthorNamesDTO>) authorService.convertToDTO(authors));
        publication.setCategories((ArrayList<String>) categoryService.convertToDTO(categories));

        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @PatchMapping("/{publicationId}")
    public ResponseEntity<?> updatePublication(@PathVariable String publicationId, @RequestBody PublicationDTO publicationDTO)
    {

        Publication publication = publicationService.findFirstById(publicationId);

        if(publication == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ArrayList<Author> authors = new ArrayList<>();

        for(AuthorNamesDTO author : publicationDTO.getAuthors())
        {
            Author authorEntity = authorService.findFirstByNameAndSurname(author.getName(), author.getSurname());

            if(authorEntity == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            authors.add(authorEntity);
        }

        ArrayList<Category> categories = new ArrayList<>();

        for(String category : publicationDTO.getCategories())
        {
            Category categoryEntity = categoryService.findFirstByName(category);

            if(categoryEntity == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            categories.add(categoryEntity);
        }

        publicationAuthorService.deleteAllPublicationAuthors(publicationId);
        publicationCategoryService.deleteAllPublicationCategories(publicationId);

        for (Author author : authors)
        {
            PublicationAuthor publicationAuthor = new PublicationAuthor();

            publicationAuthor.setPublication_id(publicationId);
            publicationAuthor.setAuthor_id(author.getId());

            publicationAuthorService.save(publicationAuthor);
        }

        for (Category category : categories)
        {
            PublicationCategory publicationCategory = new PublicationCategory();

            publicationCategory.setPublication_id(publicationId);
            publicationCategory.setCategory_id(category.getId());

            publicationCategoryService.save(publicationCategory);
        }

        if(publicationDTO.getTitle() != null)
            publication.setTitle(publicationDTO.getTitle());

        publication = publicationService.save(publication);
        publicationDTO.setUpdated_at(publication.getUpdated_at());

        return new ResponseEntity<>(publicationDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable String publicationId)
    {
        Publication publication = publicationService.findFirstById(publicationId);

        if(publication == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        publicationService.delete(publication);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
