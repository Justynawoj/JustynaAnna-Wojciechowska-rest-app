package com.crud.tasks.services;

import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.domains.TrelloCard;
import com.crud.tasks.domains.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoards() {

        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","first",trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        Mockito.when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(trelloBoardDtoList.size(),trelloBoardDtos.size());
        Assert.assertEquals(trelloBoardDtoList.get(0).getId(),trelloBoardDtos.get(0).getId());
        Assert.assertEquals(trelloBoardDtoList.get(0).getLists(),trelloBoardDtos.get(0).getLists());
        Assert.assertEquals(trelloBoardDtoList.get(0).getName(),trelloBoardDtos.get(0).getName());


    }
}
