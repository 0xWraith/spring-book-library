package com.wraith.dbs.services;

import com.wraith.dbs.dto.CardDTO;
import com.wraith.dbs.interfaces.IService;
import com.wraith.dbs.models.Card;
import com.wraith.dbs.repositories.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CardService implements IService<Card, CardDTO>
{

    private final ModelMapper modelMapper;
    private final CardRepository cardRepository;

    @Autowired
    public CardService(ModelMapper modelMapper, CardRepository cardRepository)
    {
        this.modelMapper = modelMapper;
        this.cardRepository = cardRepository;
    }

    @Override
    public Card save(Card card)
    {
        card.setUpdated_at(OffsetDateTime.now());
        return cardRepository.save(card);
    }

    @Override
    public void delete(Card entity) { cardRepository.delete(entity); }

    public Card convertToEntity(CardDTO cardDTO) { return modelMapper.map(cardDTO, Card.class); }

    @Override
    public CardDTO convertToDTO(Card entity) { return modelMapper.map(entity, CardDTO.class); }

    public Card findFirstById(String cardId) { return cardRepository.findFirstById(cardId); }
}
