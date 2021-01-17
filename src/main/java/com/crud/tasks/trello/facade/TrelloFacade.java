package com.crud.tasks.trello.facade;

import com.crud.tasks.domains.*;
import com.crud.tasks.mappers.TrelloMapper;
import com.crud.tasks.services.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class TrelloFacade {

    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;
    private final TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToTrelloCardDto(trelloCard));
    }
}
