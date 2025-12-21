package com.inmemDBexample.inmemCRUD.controller;

import com.inmemDBexample.inmemCRUD.domain.dto.TaskDto;
import com.inmemDBexample.inmemCRUD.domain.entities.Task;
import com.inmemDBexample.inmemCRUD.mappers.TaskMapper;
import com.inmemDBexample.inmemCRUD.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tasks-list/{task_list_id}/tasks")
public class TasksController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId,
                              @RequestBody TaskDto taskDto) {
        Task createdTask = taskService.createTask(
                taskListId,
                taskMapper.fromDTO(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }
}
