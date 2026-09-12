package uz.pdp.todo.backend.utils;


import uz.pdp.todo.backend.dtos.*;
import uz.pdp.todo.backend.modules.Task;
import uz.pdp.todo.backend.modules.*;

public class Converter {
    public Task fromCreateDtoToTask(TaskCreateDTO createDTO) {
        if (createDTO.title().length() > 20){
            System.out.println("Title is too long, please try again!");
        }
        return new Task(
                createDTO.title(),
                createDTO.description(),
                createDTO.priority(),
                createDTO.category());

    }

    public Task fromUpdateDtoToTask(TaskUpdateDTO updateDto) {
        if (updateDto.title().length() > 20){
            System.out.println("Title is too long, please try again!");
        }
        return new Task(
                updateDto.title(),
                updateDto.description(),
                updateDto.priority(),
                updateDto.category());
    }
}
