package uz.pdp.todo.backend.service;

import uz.pdp.todo.backend.dtos.TaskCreateDTO;
import uz.pdp.todo.backend.dtos.TaskUpdateDTO;
import uz.pdp.todo.backend.modules.Task;

import java.util.List;

public interface TodoService {

    Task create(TaskCreateDTO dto);

    boolean update(Long id, TaskUpdateDTO updateDTO);

    boolean delete(Long id);

    Task getTaskById(Long id);

    List<Task> getAll();

    boolean complete(Long id);
}

