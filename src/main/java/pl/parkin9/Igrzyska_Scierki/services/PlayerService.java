/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.Set;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
public interface PlayerService {

    void savePlayer(Player player);

    Set<Player> findAllPlayers(UsersAccount usersAccount);
}
