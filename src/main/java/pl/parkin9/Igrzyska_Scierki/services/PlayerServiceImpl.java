/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.repositories.PlayerRepository;

/**
 * @author parkin9
 *
 */

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
///////////////////////////////////////////////////////////////////////

    @Override
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }
    
    @Override
    public Set<Player> findAllPlayers(UsersAccount usersAccount) {
        
        return playerRepository.findAllWithLoggedUsersAccount(usersAccount);
    }
}
