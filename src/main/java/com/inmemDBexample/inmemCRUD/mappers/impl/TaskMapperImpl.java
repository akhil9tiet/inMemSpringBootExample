package com.inmemDBexample.inmemCRUD.mappers.impl;

import com.inmemDBexample.inmemCRUD.domain.dto.TaskDto;
import com.inmemDBexample.inmemCRUD.domain.entities.Task;
import com.inmemDBexample.inmemCRUD.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    public Task fromDTO(TaskDto taskDto) {
        return new Task(
            taskDto.id(),
            taskDto.title(),
            taskDto.description(),
            taskDto.dueDate(),
            taskDto.status(),
            taskDto.priority(),
            null,
            null,
            null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus()
        );
    }
}
