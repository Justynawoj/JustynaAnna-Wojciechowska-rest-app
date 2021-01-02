package com.crud.tasks.services;

import com.crud.tasks.domains.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {

   /*     //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if(mail.getToCc()!=null) {
            mailMessage.setCc(mail.getToCc());
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender,times(1)).send(mailMessage);*/


        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(Mockito.any(MimeMessagePreparator.class));
    }
}

