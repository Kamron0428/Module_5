package uz.pdp.todo.backend.utils;

import uz.pdp.todo.backend.dtos.TaskCreateDTO;
import uz.pdp.todo.backend.dtos.TaskUpdateDTO;
import uz.pdp.todo.backend.modules.Task;

public class Converter {
    public Task fromCreateDtoToTask(TaskCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }
        return new Task(
                createDTO.title(),
                createDTO.description(),
                createDTO.priority(),
                createDTO.category());
    }

    public Task fromUpdateDtoToTask(TaskUpdateDTO updateDto) {
        if (updateDto == null) {
            return null;
        }
        return new Task(
                updateDto.title(),
                updateDto.description(),
                updateDto.priority(),
                updateDto.category());
    }
}

