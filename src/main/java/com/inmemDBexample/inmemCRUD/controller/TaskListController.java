package com.inmemDBexample.inmemCRUD.controller;

import com.inmemDBexample.inmemCRUD.domain.dto.TaskListDto;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;
import com.inmemDBexample.inmemCRUD.mappers.TaskListMapper;
import com.inmemDBexample.inmemCRUD.services.TaskListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path="/task-lists")
public class TaskListController {

    private static final Logger logger = LoggerFactory.getLogger(TaskListController.class);

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> getTaskLists() {
        try {
            logger.info("Fetching all task lists");
            List<TaskListDto> result = taskListService.listTaskLists()
                    .stream()
                    .map(taskListMapper::toDto)
                    .toList();
            logger.info("Successfully fetched {} task lists", result.size());
            return result;
        } catch (Exception e) {
            logger.error("Error fetching task lists", e);
            throw e;
        }
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
       TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDTO(taskListDto)
       );
         return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path="/{task_list_id")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto);
    }
}
