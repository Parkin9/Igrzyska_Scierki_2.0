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

    Player getOneById(Long id);

    List<Player> getAllPlayers(UsersAccount usersAccount);

    void deletePlayer(Long id);

    Boolean checkingIfPlayerNameAlreadyExists(List<Player> players, Player checkedPlayer);

    Player comparePlayersScoresAndChooseTheWinner(List<Player> players);

}
