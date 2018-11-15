/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.parkin9.Igrzyska_Scierki.models.Task;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    @Query(value = "select t from Task t where t.usersAccount = ?1")
    List<Task> findAllWithLoggedUsersAccount(UsersAccount usersAccount);
}
