package com.inmemDBexample.inmemCRUD.services.impl;

import com.inmemDBexample.inmemCRUD.domain.entities.Task;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskList;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskPriority;
import com.inmemDBexample.inmemCRUD.domain.entities.TaskStatus;
import com.inmemDBexample.inmemCRUD.repositories.TaskListRepository;
import com.inmemDBexample.inmemCRUD.repositories.TaskRepository;
import com.inmemDBexample.inmemCRUD.services.TaskService;
import jakarta.transaction.Transactional;
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

    @Transactional
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

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()){
            throw new IllegalArgumentException("Task ID must be present");
        }
        if(!task.getId().equals(taskId)){
            throw new IllegalArgumentException("Attempting to change Task ID, this is not permitted");
        }

        if(null == task.getPriority()){
            throw new IllegalArgumentException("Task Priority must be present");
        }

        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task Status must be present");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(()-> new IllegalArgumentException("Task with ID "+taskId+" does not exist in Task List with ID "+taskListId));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
