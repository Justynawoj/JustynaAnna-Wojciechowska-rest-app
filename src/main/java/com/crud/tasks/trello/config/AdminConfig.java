package com.crud.tasks.trello.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.mail2}")
    private String adminMail2;

    @Value("${admin.name}")
    private String adminName;


}
