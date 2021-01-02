package com.crud.tasks.services;

import com.crud.tasks.domains.Task;
import com.crud.tasks.repositories.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    DbService dbService;

    @Test
    public void getAllTasks() {

        //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content");
        Task task2 = new Task(2L, "Task 2", "Task 2 content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> tasksRetrieved = dbService.getAllTasks();

        //Then
        Assert.assertNotNull(tasksRetrieved);
        Assert.assertTrue(tasksRetrieved.contains(task1));

    }

    @Test
    public void saveTask() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content");
        when(taskRepository.save(task1)).thenReturn(task1);
        //WHen
        Task taskSaved = dbService.saveTask(task1);
        //Then
        Assert.assertSame(task1,taskSaved);
    }
    @Test
    public void getTask() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content");
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task1));

        //WHen
        Task taskRetrieved = dbService.getTask(1L).get();

        //Then
        Assert.assertSame(task1,taskRetrieved);

    }
    @Test
    public void deleteTaskById() {
  /*      //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content");
        Task task2 = new Task(2L, "Task 2", "Task 2 content");
        when(taskRepository.save(task1)).thenReturn(task1);
        when(taskRepository.save(task2)).thenReturn(task2);
        Task task1Saved = dbService.saveTask(task1);
        Task task2Saved = dbService.saveTask(task2);

        //When
        dbService.deleteTaskById(task1.getId());

     //   Task taskRertrieved = dbService.getTask(task1.getId()).get();
        Task taskRertrieved2 = dbService.getTask(task2.getId()).get();

     //   System.out.println(taskRertrieved);
      //  System.out.println(taskRertrieved2);


        //Then

        Assert.assertFalse(dbService.getTask(task1Saved.getId()).isPresent());
       // Assert.assertTrue(dbService.getTask(task2Saved.getId()).isPresent());*/
    }
}
