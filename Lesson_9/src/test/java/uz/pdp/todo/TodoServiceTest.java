package uz.pdp.todo;

import uz.pdp.todo.backend.dtos.TaskCreateDTO;
import uz.pdp.todo.backend.dtos.TaskUpdateDTO;
import uz.pdp.todo.backend.enums.Category;
import uz.pdp.todo.backend.enums.Priority;
import uz.pdp.todo.backend.modules.Task;
import uz.pdp.todo.backend.service.TodoService;
import uz.pdp.todo.backend.service.TodoServiceImpl;

import java.util.List;

public class TodoServiceTest {
    public static void main(String[] args) {
        System.out.println("Starting TodoService tests...");
        TodoService service = new TodoServiceImpl();

        List<Task> initialTasks = service.getAll();
        System.out.println("Initial tasks count: " + initialTasks.size());

        // 1. Create task
        Task created = service.create(new TaskCreateDTO(
                "Test Task",
                "Testing description",
                Priority.HIGH,
                Category.WORK,
                false
        ));
        System.out.println("Created task: " + created.getId() + " - " + created.getTitle());
        assert created.getId() != null;
        assert "Test Task".equals(created.getTitle());

        // 2. Complete task
        boolean completed = service.complete(created.getId());
        System.out.println("Completed task: " + completed);
        assert completed;

        Task retrieved = service.getTaskById(created.getId());
        assert retrieved != null && retrieved.isCompleted();

        // 3. Update task (ensure completed remains true)
        boolean updated = service.update(created.getId(), new TaskUpdateDTO(
                "Updated Title",
                null,
                null,
                null
        ));
        System.out.println("Updated task: " + updated);
        assert updated;

        retrieved = service.getTaskById(created.getId());
        assert "Updated Title".equals(retrieved.getTitle());
        assert retrieved.isCompleted(); // completed status preserved!

        // 4. Delete test task
        boolean deleted = service.delete(created.getId());
        System.out.println("Deleted task: " + deleted);
        assert deleted;

        // 5. Test invalid delete
        boolean deletedNonExisting = service.delete(999999L);
        System.out.println("Deleted non-existing: " + deletedNonExisting);
        assert !deletedNonExisting;

        System.out.println("All TodoService tests passed successfully! ✅");
    }
}
