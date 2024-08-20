package com.karyalaya.KaryalayaEMS.service;

import com.karyalaya.KaryalayaEMS.exception.TaskException;
import com.karyalaya.KaryalayaEMS.model.Task;
import com.karyalaya.KaryalayaEMS.model.TaskStatus;
import com.karyalaya.KaryalayaEMS.model.User;
import com.karyalaya.KaryalayaEMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import com.karyalaya.KaryalayaEMS.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository employeeRepository;

    public Task createTask(Task task) {
        User employee = employeeRepository.findById(task.getAssignedTo().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        task.setAssignedTo(employee);
        return taskRepository.save(task);
    }

    public Task updateTask(Integer id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByEmployeeId(Integer employeeId) {
        User employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return taskRepository.findByAssignedTo(employee);
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTaskStatus(Integer id, TaskStatus newStatus) throws TaskException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found with id: " + id));

        // Check if the task's current status allows the transition
        if (task.getStatus() == TaskStatus.PENDING && (newStatus == TaskStatus.IN_PROGRESS || newStatus == TaskStatus.COMPLETED) ||
                                 (task.getStatus() == TaskStatus.IN_PROGRESS &&  newStatus == TaskStatus.COMPLETED)) {
            task.setStatus(newStatus);
            return taskRepository.save(task);
        } else {
            throw new TaskException("Invalid status transition.");
        }
    }
}
