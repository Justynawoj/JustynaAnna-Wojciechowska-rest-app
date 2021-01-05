package com.crud.tasks.services;

import com.crud.tasks.trello.config.ActuatorConfig;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private ActuatorConfig actuatorConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        boolean isOnceADayMessage = message.contains("Currently");
        DayOfWeek today = LocalDate.now().getDayOfWeek();

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("companyName", actuatorConfig.getCompanyName());
        context.setVariable("companyPhone", actuatorConfig.getCompanyPhone());
        context.setVariable("companyEmail", actuatorConfig.getCompanyEmail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("is_once_a_day_message",isOnceADayMessage);
        context.setVariable("day_of_week", today);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
