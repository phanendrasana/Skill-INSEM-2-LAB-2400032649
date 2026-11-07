package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindTask() {
        Task task = new Task();
        task.setTitle("Complete Integration Testing");
        task.setDescription("Test JPA repository operations");

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId(), "Generated ID should not be null");

        Optional<Task> retrievedTask =
                taskRepository.findById(savedTask.getId());

        assertTrue(retrievedTask.isPresent(), "Saved task should be present");
        assertEquals("Complete Integration Testing",
                retrievedTask.get().getTitle());
        assertEquals("Test JPA repository operations",
                retrievedTask.get().getDescription());
    }
}
