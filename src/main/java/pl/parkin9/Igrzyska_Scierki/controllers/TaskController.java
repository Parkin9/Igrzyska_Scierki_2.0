/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.parkin9.Igrzyska_Scierki.models.Task;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.InputValidationService;
import pl.parkin9.Igrzyska_Scierki.services.TaskService;

/**
 * @author parkin9
 *
 */

@Controller
public class TaskController {

    private final TaskService taskService;
    private final InputValidationService inputValidationService;
    private List<Task> tasks = null;
    
    @Autowired
    public TaskController(TaskService taskService, InputValidationService inputValidationService) {
        this.taskService = taskService;
        this.inputValidationService = inputValidationService;
    }
    
//////////////////////////////////////////////////////////////////////
    
    @GetMapping("/addTask")
    public ModelAndView showTaskForm(@ModelAttribute(value="validErrorsMessages") String validErrorsMessages, 
                                     @RequestParam(value="inputError", required=false) String inputErrorParam, 
                                                                                       HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("addTask");
        
        modelAndView.addObject("task", new Task());
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        tasks = taskService.getAllTasks(usersAccount);
        modelAndView.addObject("tasks", tasks);
        
        if(inputErrorParam != null) {
            
            switch(inputErrorParam) {
            
            case "taskAlreadyExists":
                modelAndView.addObject("taskExists", "*Zadanie o podanej nazwie już istnieje.");
                break;
                
            case "validationError":
                List<String> errorsMessages = Arrays.asList(validErrorsMessages.split(","));
                modelAndView.addObject("errorsMessages", errorsMessages);
                break;
            }
        }
        
        return modelAndView;
    }
    
    @PostMapping("/addTask")
    public ModelAndView addNewTask(@Valid Task task, 
                                          BindingResult bindingResult, 
                                          HttpSession session, 
                                          RedirectAttributes redirectAttributes) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        // Checking if a Hibernate Validation returns any errors.
        // If yes, pulling they from BindingResult and redirect to GET/addPlayer.
        if(bindingResult.hasErrors()) {
            List<String> errorsMessages = inputValidationService.getAllMessagesFromBindingResultAsList(bindingResult);
            redirectAttributes.addFlashAttribute("validErrorsMessages", errorsMessages);
            modelAndView.setViewName("redirect:/addTask?inputError=validationError");
            
            return modelAndView;
        }// END
        
        // Checking if the provided Task, with a concrete TaskName, already exists in a database.
        if(taskService.checkingIfTaskNameAlreadyExists(tasks, task)) {
            modelAndView.setViewName("redirect:/addTask?inputError=taskAlreadyExists");
            
            return modelAndView;
        }// END
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
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
