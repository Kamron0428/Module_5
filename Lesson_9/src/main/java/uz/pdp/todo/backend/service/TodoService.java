package uz.pdp.todo.backend.service;


import uz.pdp.todo.backend.dtos.*;
import uz.pdp.todo.backend.modules.Task;

import java.util.List;

public interface TodoService {

    Task create(TaskCreateDTO dto);

    void update(Long id, TaskUpdateDTO updateDTO);

    void delete(Long id);

    Task getTaskById(Long id);

    List<Task> getAll();

    void complete(Long id);
}
