package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.CardDTO;
import com.wraith.dbs.models.Card;
import com.wraith.dbs.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController
{

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) { this.cardService = cardService; }

    @PostMapping("")
    public ResponseEntity<?> createCard(@RequestBody CardDTO cardDTO)
    {

        if(cardDTO.isDTOInvalid())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Card card = cardService.convertToEntity(cardDTO);

        card = cardService.save(card);

        return new ResponseEntity<>(cardService.convertToDTO(card), HttpStatus.CREATED);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<?> getCard(@PathVariable String cardId)
    {
        Card card = cardService.findFirstById(cardId);

        if(card == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cardService.convertToDTO(card), HttpStatus.OK);
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<?> updateCard(@PathVariable String cardId, @RequestBody CardDTO cardDTO)
    {

        Card card = cardService.findFirstById(cardId);

        if(card == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(cardDTO.getStatus() != null)
            card.setStatus(cardDTO.getStatus());

        if(cardDTO.getUser_id() != null)
            card.setUser_id(cardDTO.getUser_id());

        card = cardService.save(card);

        return new ResponseEntity<>(cardService.convertToDTO(card), HttpStatus.OK);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable String cardId)
    {
        Card card = cardService.findFirstById(cardId);

        if(card == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        cardService.delete(card);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
