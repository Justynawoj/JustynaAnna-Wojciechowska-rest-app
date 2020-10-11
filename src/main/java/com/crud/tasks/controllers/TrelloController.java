package com.crud.tasks.controllers;

import com.crud.tasks.domains.CreatedTrelloCard;
import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.domains.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;


    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloClient.getTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){

        return trelloClient.createNewCard(trelloCardDto);
    }

  /*  //My 22.2
    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        List<TrelloBoardDto>  result = trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getId() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName().toLowerCase().contains("kodilla"))
                .peek(System.out::println)
                .collect(Collectors.toList());
        return result;
    }*/

}