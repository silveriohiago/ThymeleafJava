package net.atos.acelerajava.todo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import net.atos.acelerajava.todo.model.Task;
import net.atos.acelerajava.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/inicio")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/formulario")
    public String formulario(Model model) {

        model.addAttribute("isUpdatePage", false);
        model.addAttribute("tasks", this.taskService.getAllTasks());
        model.addAttribute("fields", new Task());

        return "formulario";
    }

    @GetMapping("/formulario/alterar/{id}")
    public String formularioAlterar(HttpServletRequest request,
                             Model model,
                             @PathVariable(required = false) Long id) {

        boolean containsUpdate = request.getRequestURI().contains("/alterar");

        model.addAttribute("isUpdatePage", containsUpdate);
        model.addAttribute("paramId", id);
        model.addAttribute("tasks", this.taskService.getAllTasks());
        model.addAttribute("fields", this.taskService.findTaskById(id));

        return "formulario";
    }

    @PostMapping("/salvar")
    public String saveForm(@ModelAttribute("tasks") Task task){
        System.out.println("ObjectTask:"+task);
        System.out.println("ServiceObjectAntes:"+this.taskService.getAllTasks());
        this.taskService.createTask(task);
        System.out.println("ServiceObjectDepois:"+this.taskService.getAllTasks());
        System.out.println("Salvo");
        return "redirect:/formulario";
    }


    @PostMapping("/atualizar")
    public String updateTask(@ModelAttribute("task") Task task) {
        System.out.println("ObjectTask:"+task);
        System.out.println("ServiceObjectAntes:"+this.taskService.getAllTasks());
        this.taskService.updateTaskById(task);
        System.out.println("ServiceObjectDepois:"+this.taskService.getAllTasks());
        System.out.println("Atualizado");
        return "redirect:/formulario"; // Redirect back to the form page after update
    }


    @GetMapping("/deletar/{id}")
    public String deleteItem( @PathVariable Long id) {
        System.out.println("Id deletado:"+id);
        System.out.println("ServiceObjectAntes:"+this.taskService.getAllTasks());
        this.taskService.deleteTask(id);
        System.out.println("ServiceObjectDepois:"+this.taskService.getAllTasks());
        System.out.println("Item Deletado:"+this.taskService.findTaskById(id).getDescription());
        return "redirect:/formulario"; // Redirect back to the items page after deletion
    }

}
