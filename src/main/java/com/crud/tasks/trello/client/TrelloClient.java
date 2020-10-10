package com.crud.tasks.trello.client;

import com.crud.tasks.domains.TrelloBoardDto;
import com.crud.tasks.exceptions.EmptyListException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.app.username}")
    public String username;


    public List<TrelloBoardDto> getTrelloBoards()  {

        URI url = buildURL();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

/*            if (boardsResponse != null) {
                return Arrays.asList(boardsResponse);
            } else {
                return new ArrayList<>();
            }*/

        return Optional
                .ofNullable(boardsResponse)
                .map(res -> Arrays.asList(res))
                .orElseThrow(()-> new EmptyListException());
         //       .orElse(new ArrayList<>());

    }

    private URI buildURL() {
        return UriComponentsBuilder.fromHttpUrl(
                trelloApiEndpoint + "/members/" + username + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

    }
}
