package com.crud.tasks.com.crud.tasks.scheduler;

import com.crud.tasks.domains.Mail;
import com.crud.tasks.repositories.TaskRepository;
import com.crud.tasks.services.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;


    @Scheduled(cron = "0 0 10 * * *")
   // @Scheduled(fixedDelay = 1000000)
  //  @Scheduled(cron = "0 0 0 25 12 ?")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        String suffix = size > 1? "tasks":"tasks";
        simpleEmailService.send(new Mail(adminConfig.getAdminMail2(),SUBJECT,String.format("Currently in database you got: %s %s", size, suffix)));
    }
}







