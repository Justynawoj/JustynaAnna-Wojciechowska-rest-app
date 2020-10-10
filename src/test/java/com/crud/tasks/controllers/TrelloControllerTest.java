package com.crud.tasks.controllers;

import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

class TrelloControllerTest {

    @Autowired
    TrelloClient trelloClient;

    @Autowired
    RestTemplate restTemplate;


    @Test
    void getTrelloBoards() {

        //Given
        TrelloClient trelloClient = new TrelloClient(restTemplate, "http://any.com", "", "", "");
        doReturn(new TrelloBoardDto[0]).when(restTemplate).getForObject(any(), any());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloClient.getTrelloBoards();

        //Then
        assertEquals(trelloBoardDtos, new ArrayList<>());

    }

    @Test
    void getTrelloBoards_nullResponse() {

        //Given
        TrelloClient trelloClient = new TrelloClient(restTemplate, "http://any.com", "", "", "");
        doReturn(null).when(restTemplate).getForObject(any(), any());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloClient.getTrelloBoards();

        //Then
        assertEquals(trelloBoardDtos, new ArrayList<>());

    }

}