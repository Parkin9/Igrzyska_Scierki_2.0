/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.List;

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
    public Player getOneById(Long id) {
        return playerRepository.getOne(id);
    }
    
    @Override
    public List<Player> getAllPlayers(UsersAccount usersAccount) {
        return playerRepository.findAllWithUsersAccount(usersAccount);
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
    
    @Override
    public Boolean checkingIfPlayerNameAlreadyExists(List<Player> players, Player checkedPlayer) {
        
        for(Player player : players) {
            
            if(checkedPlayer.getPlayerName().equals(player.getPlayerName())) {
                return true;
            }            
        }
        
        return false;
    }
    
    @Override
    public Player comparePlayersScoresAndChooseTheWinner(List<Player> players) {
        
        Integer highestScore = 0;
        Player winnerPlayer = null;
        
        for(Player player : players) {

            if(player.getScore() > highestScore) {
                highestScore = player.getScore();
            }

            if(player.getScore().equals(highestScore)) {
                winnerPlayer = player;
            }
        }
        
        return winnerPlayer;
    }
}