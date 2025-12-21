package com.inmemDBexample.inmemCRUD.services.impl;

import com.inmemDBexample.inmemCRUD.domain.entities.Task;
import com.inmemDBexample.inmemCRUD.repositories.TaskRepository;
import com.inmemDBexample.inmemCRUD.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }
}
