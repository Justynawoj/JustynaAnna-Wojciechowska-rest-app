package com.crud.tasks.services;

import com.crud.tasks.domains.CreatedTrelloCardDto;
import com.crud.tasks.domains.Mail;
import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.domains.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    private final String SUBJECT = "Task: New Trello card";

    public List <TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto){
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: "+trelloCardDto.getName() + "has been created on your trello account")));
        return newCard;
    }
}
