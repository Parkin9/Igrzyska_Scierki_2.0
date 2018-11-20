/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.parkin9.Igrzyska_Scierki.models.Task;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.repositories.TaskRepository;

/**
 * @author parkin9
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
////////////////////////////////////////////////////////////////////
    
    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }
    
    @Override
    public List<Task> getAllTasks(UsersAccount usersAccount) {
        return taskRepository.findAllWithUsersAccount(usersAccount);
    }
    
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    @Override
    public Boolean checkingIfTaskNameAlreadyExists(List<Task> tasks, Task checkedTask) {
        
        for(Task task : tasks) {
            
            if(checkedTask.getTaskName().equals(task.getTaskName())) {
                return true;
            }
        }
        
        return false;
    }
}
