/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import pl.parkin9.Igrzyska_Scierki.models.Game;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
public interface GameService {

    void saveGame(Game game);

    Game getOneByUsersAccountAndActive(UsersAccount usersAccount);

}
