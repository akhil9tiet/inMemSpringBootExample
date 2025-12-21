package com.inmemDBexample.inmemCRUD.services.impl;

import com.inmemDBexample.inmemCRUD.domain.entities.Task;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskPriority;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskStatus;
import com.inmemDBexample.inmemCRUD.repositories.TaskListRepository;
import com.inmemDBexample.inmemCRUD.repositories.TaskRepository;
import com.inmemDBexample.inmemCRUD.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null!= task.getId()){
            throw new IllegalArgumentException("Task already has an ID");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title must be present");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(()-> new IllegalArgumentException("Task List with ID "+taskListId+" does not exist"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                now,
                now,
                taskList
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }
}
