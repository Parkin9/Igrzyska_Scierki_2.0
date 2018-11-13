/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

//    List<Player> findByUsersAccount(UsersAccount usersAccount);
    
    @Query(value = "select p from Player p where p.usersAccount = ?1 and p.playerName = ?2")
    Player findPlayerWithLoggedUsersAccount(UsersAccount usersAccount, String playerName);
}