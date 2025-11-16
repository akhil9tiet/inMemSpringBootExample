package com.inmemDBexample.inmemCRUD.mappers;

import com.inmemDBexample.inmemCRUD.domain.dto.TaskListDto;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDTO(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
