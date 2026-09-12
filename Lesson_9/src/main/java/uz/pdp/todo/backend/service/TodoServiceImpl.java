package uz.pdp.todo.backend.service;


import uz.pdp.todo.backend.dtos.*;
import uz.pdp.todo.backend.modules.Task;
import uz.pdp.todo.backend.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TodoServiceImpl implements TodoService {
    Converter converter = new Converter();
    List<Task> tasks = new ArrayList<>();

    @Override
    public Task create(TaskCreateDTO dto) {
        Task task = converter.fromCreateDtoToTask(dto);
        tasks.add(task);
        return task;
    }

    @Override
    public void update(Long id, TaskUpdateDTO updateDTO) {
        Task task = converter.fromUpdateDtoToTask(updateDTO);
        IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .ifPresent(i -> tasks.set(i, task));
    }

    @Override
    public void delete(Long id) {
        IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .ifPresent(i -> tasks.remove(i));
    }

    @Override
    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public void complete(Long id) {
        IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .ifPresent(i -> tasks.get(i).setCompleted(true));
    }
}
