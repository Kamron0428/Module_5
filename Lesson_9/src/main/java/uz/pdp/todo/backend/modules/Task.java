package uz.pdp.todo.backend.modules;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.todo.backend.enums.Category;
import uz.pdp.todo.backend.enums.Priority;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@NoArgsConstructor
public class Task {
    private static final AtomicLong idIncrement = new AtomicLong(1);
    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private Category category;
    private boolean completed;

    public Task(String title, String description, Priority priority, Category category) {
        this.id = idIncrement.getAndIncrement();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.completed = false;
    }
}