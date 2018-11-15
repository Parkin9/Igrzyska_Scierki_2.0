/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.List;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
public interface PlayerService {

    void savePlayer(Player player);

    List<Player> getAllPlayers(UsersAccount usersAccount);

    Player getOneById(Long id);

    void deletePlayer(Player player);

    Boolean checkingIfPlayerAlreadyExists(List<Player> players, Player checkedPlayer);
}
