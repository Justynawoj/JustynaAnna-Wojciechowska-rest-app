package com.crud.tasks.trello.client;

import com.crud.tasks.domains.CreatedTrelloCard;
import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.domains.TrelloCardDto;
import com.crud.tasks.exceptions.EmptyListException;
import com.crud.tasks.trello.config.TrelloConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TrelloConfig trelloConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);


    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = buildURL();

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));

        } catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
/*    pierwsza wersja
      if (boardsResponse != null) {
    public List<TrelloBoardDto> getTrelloBoards()  {
        URI url = buildURL();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
/*            if (boardsResponse != null) {
                return Arrays.asList(boardsResponse);
            } else {
                return new ArrayList<>();
            }*/


/*         druga wersja
        return Optional
                .ofNullable(boardsResponse)
                .map(res -> Arrays.asList(res))
                .orElseThrow(()-> new EmptyListException());
         //       .orElse(new ArrayList<>());
*/
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloAppToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();


        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }

    private URI buildURL() {
        return UriComponentsBuilder.fromHttpUrl(
                trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloAppToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

    }
}
