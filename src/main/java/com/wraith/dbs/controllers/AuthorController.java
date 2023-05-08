package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.authors.AuthorFullDTO;
import com.wraith.dbs.models.Author;
import com.wraith.dbs.services.AuthorService;
import com.wraith.dbs.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/authors")
public class AuthorController
{

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) { this.authorService = authorService; }

    @PostMapping("")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorFullDTO authorDTO)
    {

        if(authorDTO.isDTOInvalid())
            return ResponseEntity.badRequest().build();

        Author author;

        author = authorService.findFirstByNameAndSurname(authorDTO.getName(), authorDTO.getSurname());

        if(author != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        author = authorService.findById(authorDTO.getId());

        if (author != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        author = authorService.save(authorService.convertToEntity(authorDTO));

        return new ResponseEntity<>(authorService.convertToDTO(author), HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable String authorId)
    {
        Author author = authorService.findFirstById(authorId);

        if(author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(authorService.convertToDTO(author), HttpStatus.OK);
    }

    @PatchMapping("/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable String authorId, @RequestBody AuthorFullDTO authorDTO)
    {

        Author author = authorService.findFirstById(authorId);

        if(author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(authorDTO.getName() != null)
            author.setName(authorDTO.getName());

        if(authorDTO.getSurname() != null)
            author.setSurname(authorDTO.getSurname());

        author.setUpdated_at(OffsetDateTime.now());

        author = authorService.save(author);

        return new ResponseEntity<>(authorService.convertToDTO(author), HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable String authorId)
    {
        Author author = authorService.findFirstById(authorId);

        if(author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        authorService.delete(author);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
