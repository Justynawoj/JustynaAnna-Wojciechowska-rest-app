package com.crud.tasks.services;

import com.crud.tasks.domains.Task;
import com.crud.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;


    public List<Task> getAllTasks(){
        return repository.findAll();
    }


    public Task saveTask(final Task task){
        return repository.save(task);
    }

    public Optional<Task> getTask(final long id){
        return repository.findById(id);
    }

    public void deleteTaskById(final long id){
        repository.deleteById(id);
    }



}
