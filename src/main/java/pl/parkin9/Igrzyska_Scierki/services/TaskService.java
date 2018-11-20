/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.List;

import pl.parkin9.Igrzyska_Scierki.models.Task;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
public interface TaskService {

    void saveTask(Task task);

    List<Task> getAllTasks(UsersAccount usersAccount);

    void deleteTask(Long id);

    Boolean checkingIfTaskNameAlreadyExists(List<Task> tasks, Task checkedTask);

}
