package net.atos.acelerajava.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.acelerajava.todo.model.Task;
import net.atos.acelerajava.todo.repository.TaskRepository;
import net.atos.acelerajava.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/inicio")
    public String home(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("task", new Task());
        return "form";
    }

    @PostMapping("/salvar")
    public String saveForm(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }

}
