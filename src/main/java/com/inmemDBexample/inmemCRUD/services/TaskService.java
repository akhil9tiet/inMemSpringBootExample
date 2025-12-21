package com.inmemDBexample.inmemCRUD.services;

import com.inmemDBexample.inmemCRUD.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
}
