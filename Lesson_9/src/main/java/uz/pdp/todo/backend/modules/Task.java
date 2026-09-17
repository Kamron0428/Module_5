package uz.pdp.todo.backend.modules;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.todo.backend.enums.Category;
import uz.pdp.todo.backend.enums.Priority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Task {
    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private Category category;
    private boolean completed;

    public Task(String title, String description, Priority priority, Category category) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.completed = false;
    }
}