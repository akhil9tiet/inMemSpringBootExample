package com.inmemDBexample.inmemCRUD.domain.dto;

import com.inmemDBexample.inmemCRUD.domain.entities.TaskPriority;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for Task entity
 * Uses Java Record for immutability
 * By using record, we get constructor, getters, equals, hashCode and toString methods automatically
 * Each field is immutable and hence no setters are provided, only getters
 * no taskList to avoid circular references and will be handled separately
 * omitting created and updated fields in DTO for simplicity
 */
public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskPriority priority,
    TaskStatus status
) {
}
