package com.crud.tasks.controllers;

import com.crud.tasks.domains.CreatedTrelloCardDto;
import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.domains.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards(){
        return trelloFacade.fetchBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);
    }

//    @Autowired
//    private TrelloService trelloService;
//
//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards() {
//       // return trelloClient.getTrelloBoards();
//        return trelloService.fetchTrelloBoards();
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
//    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
//      //  return trelloClient.createNewCard(trelloCardDto);
//        return trelloService.createTrelloCard(trelloCardDto);
//    }

}