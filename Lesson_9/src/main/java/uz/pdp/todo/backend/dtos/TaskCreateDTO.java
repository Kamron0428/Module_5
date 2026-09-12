package uz.pdp.todo.backend.dtos;

import uz.pdp.todo.backend.enums.*;

public record TaskCreateDTO(String title,
                            String description,
                            Priority priority,
                            Category category,
                            boolean completed) {

    public TaskCreateDTO {
        if (priority == null) {
            priority = Priority.LOW;
        }
        if (category == null){
            category = Category.STUDY;
        }
    }
}
