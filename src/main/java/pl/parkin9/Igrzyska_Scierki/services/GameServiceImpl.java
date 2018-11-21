/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.parkin9.Igrzyska_Scierki.models.Game;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.repositories.GameRepository;


/**
 * @author parkin9
 *
 */
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

///////////////////////////////////////////////////////////////

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Game getOneByUsersAccountAndActive(UsersAccount usersAccount) {
        return gameRepository.findFirstByUsersAccountAndActiveIsTrue(usersAccount);
    }
    
    @Override
    public Integer compareEndGameDateWithToday(Game game) {
        
        Integer value = 0;
        LocalDate endGameDate = LocalDate.parse(game.getEnd().toString());
        LocalDate currentDate = LocalDate.now();
        
        if(endGameDate.isEqual(currentDate)) {
            value = 0;
            
        } else if(currentDate.isAfter(endGameDate)) {
            value = 1;
            
        } else if(currentDate.isBefore(endGameDate)) {
            value = -1;
        }
        
        return value;
    }
}
