package com.inmemDBexample.inmemCRUD.controller;

import com.inmemDBexample.inmemCRUD.domain.dto.TaskListDto;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;
import com.inmemDBexample.inmemCRUD.mappers.TaskListMapper;
import com.inmemDBexample.inmemCRUD.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> getTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
       TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDTO(taskListDto)
       );
         return taskListMapper.toDto(createdTaskList);
    }
}
