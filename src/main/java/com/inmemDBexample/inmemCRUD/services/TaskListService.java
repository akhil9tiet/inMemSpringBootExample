package com.inmemDBexample.inmemCRUD.services;

import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
}
