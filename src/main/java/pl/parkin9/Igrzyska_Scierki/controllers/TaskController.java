/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Task;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.TaskService;

/**
 * @author parkin9
 *
 */

@Controller
public class TaskController {

    private final TaskService taskService;
    
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
//////////////////////////////////////////////////////////////////////
    
    @GetMapping("/addTask")
    public ModelAndView showTaskForm(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("addTask");
        
        modelAndView.addObject("task", new Task());
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        List<Task> tasks = taskService.getAllTasks(usersAccount);
        modelAndView.addObject("tasks", tasks);
        
        return modelAndView;
    }
    
    @PostMapping("/addTask")
    public ModelAndView addNewTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("addTask");
            return modelAndView;
        }
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        
        // Checking if the provided Task, "under" the logged in UsersAccount, already exists in a database.
        List<Task> tasks = taskService.getAllTasks(usersAccount);
        if(taskService.checkingIfTaskAlreadyExists(tasks, task)) {
            
            modelAndView.addObject("errorAddTask", "Zadanie o podanej nazwie już istnieje.");
            modelAndView.setViewName("addTask");
            return modelAndView;
        }
        // The end of a checking the existence.
        
        task.setUsersAccount(usersAccount);
        taskService.saveTask(task);
        
        modelAndView.setViewName("redirect:/addTask");
                
        return modelAndView;
    }
    
    @GetMapping("/deleteTask/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("redirect:/addTask");
        
        taskService.deleteTask(id);
        
        return modelAndView;
    }
}
