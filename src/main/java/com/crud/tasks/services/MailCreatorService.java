package com.crud.tasks.services;

import com.crud.tasks.trello.config.ActuatorConfig;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("companyName", actuatorConfig.getCompanyName());
        context.setVariable("companyPhone", actuatorConfig.getCompanyPhone());
        context.setVariable("companyEmail", actuatorConfig.getCompanyEmail());

        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
