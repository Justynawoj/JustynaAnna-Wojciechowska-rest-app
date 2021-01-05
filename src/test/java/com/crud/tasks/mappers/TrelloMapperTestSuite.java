package com.crud.tasks.mappers;

import com.crud.tasks.domains.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    TrelloMapper trelloMapper;

    TrelloListDto trelloListDto1 = new TrelloListDto("1","fist list", true);
    TrelloListDto trelloListDto2 = new TrelloListDto("2","second list", false);
    List<TrelloListDto> trelloListDtos = new ArrayList<>();
    TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","first",trelloListDtos);
    List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();

    @Test
    public void mapToBoardsTest() {

        //Given
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        trelloBoardDtoList.add(trelloBoardDto1);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        Assert.assertEquals(trelloBoardDtoList.size(),trelloBoardList.size());
        Assert.assertEquals(trelloBoardList.get(0).getName(),trelloBoardList.get(0).getName());
    }

    @Test
    public void mapToList() {
        //Given
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(trelloListDtos.size(),trelloLists.size());
        Assert.assertEquals(trelloListDtos.get(0).getName(), trelloLists.get(0).getName());
        Assert.assertNotNull(trelloLists);
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("3","third list", true);
        TrelloList trelloList2 = new TrelloList("4","fourth list", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1","first",trelloLists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> listOfTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        Assert.assertEquals(1,listOfTrelloBoardDto.size());
        Assert.assertEquals(trelloBoardList.get(0).getId(),listOfTrelloBoardDto.get(0).getId());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("3","third list", true);
        TrelloList trelloList2 = new TrelloList("4","fourth list", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        //When
        List<TrelloListDto> listOfTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(2,listOfTrelloListDto.size());
        Assert.assertEquals(trelloLists.size(),listOfTrelloListDto.size());
    }

    @Test
    public void mapToTrelloCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("fist", "firstCard","pos 1", "21");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);
        //Then
        Assert.assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
    }

    @Test
    public void mapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("fist", "firstCard","pos 1", "21");
        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals(trelloCardDto.getName(),trelloCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(),trelloCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(),trelloCard.getListId());

    }
}
