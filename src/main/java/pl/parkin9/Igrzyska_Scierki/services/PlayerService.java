/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
public interface PlayerService {

    void savePlayer(Player player);

    Player checkIfPlayerExists(UsersAccount usersAccount, String playerName);
}
