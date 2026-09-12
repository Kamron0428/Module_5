package uz.pdp.todo.backend.dtos;


import uz.pdp.todo.backend.enums.*;

public record TaskUpdateDTO(String title,
                            String description,
                            Priority priority,
                            Category category){
}
