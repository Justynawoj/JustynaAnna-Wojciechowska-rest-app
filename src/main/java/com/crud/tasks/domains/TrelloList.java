package com.crud.tasks.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrelloList {
    private String id;
    private String name;
    private boolean isClosed;
}
