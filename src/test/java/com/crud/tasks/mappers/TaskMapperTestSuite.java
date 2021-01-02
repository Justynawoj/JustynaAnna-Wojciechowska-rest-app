package com.crud.tasks.mappers;

import com.crud.tasks.domains.Task;
import com.crud.tasks.domains.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task1", "Task Description");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(taskDto.getId(),task.getId());
        Assert.assertEquals(taskDto.getTitle(),task.getTitle());
        Assert.assertEquals(taskDto.getContent(),task.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(2L,"Task title", "Task content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(task.getId(), taskDto.getId());
        Assert.assertEquals(task.getTitle(), taskDto.getTitle());
        Assert.assertEquals(task.getContent(), taskDto.getContent());

    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content");
        Task task2 = new Task(2L, "Task 2", "Task 2 content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(taskList.size(),taskDtoList.size());
        Assert.assertEquals(taskList.get(0).getId(),taskDtoList.get(0).getId());
        Assert.assertEquals(taskList.get(0).getTitle(),taskDtoList.get(0).getTitle());
        Assert.assertEquals(taskList.get(0).getContent(),taskDtoList.get(0).getContent());
        Assert.assertEquals(taskList.get(1).getId(),taskDtoList.get(1).getId());
        Assert.assertEquals(taskList.get(1).getTitle(),taskDtoList.get(1).getTitle());
        Assert.assertEquals(taskList.get(1).getContent(),taskDtoList.get(1).getContent());

    }
}
