package com.inmemDBexample.inmemCRUD.mappers;

import com.inmemDBexample.inmemCRUD.domain.dto.TaskDto;
import com.inmemDBexample.inmemCRUD.domain.entities.Task;

public interface TaskMapper {
    Task fromDTO(TaskDto taskDto);
    TaskDto toDto(Task task);
}
