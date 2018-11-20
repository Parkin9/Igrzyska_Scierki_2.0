/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.parkin9.Igrzyska_Scierki.models.Game;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

    @Query(value = "select g from Game g where g.usersAccount = ?1 and g.active = true")
    Game findFirstByUsersAccountAndActiveIsTrue(UsersAccount usersAccount);
}
