package uz.pdp.todo.backend.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.pdp.todo.backend.dtos.TaskCreateDTO;
import uz.pdp.todo.backend.dtos.TaskUpdateDTO;
import uz.pdp.todo.backend.modules.Task;
import uz.pdp.todo.backend.utils.Converter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TodoServiceImpl implements TodoService {
    private final Converter converter = new Converter();
    private final File file = new File("files/tasks.txt");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Task create(TaskCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("TaskCreateDTO cannot be null");
        }
        List<Task> currentTasks = getAll();
        Task task = converter.fromCreateDtoToTask(dto);

        long nextId = currentTasks.stream()
                .filter(t -> t.getId() != null)
                .mapToLong(Task::getId)
                .max()
                .orElse(0L) + 1;
        task.setId(nextId);
        task.setCompleted(dto.completed());

        currentTasks.add(task);
        writeToFile(currentTasks);
        return task;
    }

    @Override
    public boolean update(Long id, TaskUpdateDTO updateDTO) {
        if (id == null || updateDTO == null) {
            return false;
        }
        List<Task> currentTasks = getAll();

        for (Task task : currentTasks) {
            if (id.equals(task.getId())) {
                if (updateDTO.title() != null && !updateDTO.title().isBlank()) {
                    task.setTitle(updateDTO.title());
                }
                if (updateDTO.description() != null && !updateDTO.description().isBlank()) {
                    task.setDescription(updateDTO.description());
                }
                if (updateDTO.priority() != null) {
                    task.setPriority(updateDTO.priority());
                }
                if (updateDTO.category() != null) {
                    task.setCategory(updateDTO.category());
                }
                writeToFile(currentTasks);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        List<Task> currentTasks = getAll();
        boolean removed = currentTasks.removeIf(task -> id.equals(task.getId()));

        if (removed) {
            for (Task task : currentTasks) {
                if (task.getId() != null && task.getId() > id) {
                    task.setId(task.getId() - 1);
                }
            }

            writeToFile(currentTasks);
        }
        return removed;
    }

    @Override
    public Task getTaskById(Long id) {
        if (id == null) {
            return null;
        }
        return getAll().stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> getAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Type listType = new TypeToken<List<Task>>() {}.getType();
            List<Task> tasks = gson.fromJson(br, listType);
            return tasks != null ? tasks : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean complete(Long id) {
        if (id == null) {
            return false;
        }
        List<Task> currentTasks = getAll();
        for (Task task : currentTasks) {
            if (id.equals(task.getId())) {
                task.setCompleted(true);
                writeToFile(currentTasks);
                return true;
            }
        }
        return false;
    }

    private void writeToFile(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write(gson.toJson(tasks));
        } catch (IOException e) {
            throw new RuntimeException("Error while writing tasks to file: " + e.getMessage(), e);
        }
    }
}