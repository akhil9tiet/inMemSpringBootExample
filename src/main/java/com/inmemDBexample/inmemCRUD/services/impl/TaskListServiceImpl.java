package com.inmemDBexample.inmemCRUD.services.impl;

import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;
import com.inmemDBexample.inmemCRUD.repositories.TaskListRepository;
import com.inmemDBexample.inmemCRUD.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(taskList.getId()!=null){
           throw new IllegalArgumentException("TaskList already has an ID");
        }
        if(taskList.getTitle()==null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("TaskList title cmust be present");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }
}
