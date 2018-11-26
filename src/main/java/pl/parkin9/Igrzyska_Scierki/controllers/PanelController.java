/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Game;
import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.GameService;
import pl.parkin9.Igrzyska_Scierki.services.PlayerService;
import pl.parkin9.Igrzyska_Scierki.services.UsersAccountService;

/**
 * @author parkin9
 *
 */

@Controller
public class PanelController {
    
    private final PlayerService playerService;
    private final UsersAccountService usersAccountService;
    private final GameService gameService;
    
    @Autowired
    public PanelController(PlayerService playerService, UsersAccountService usersAccountService, GameService gameService) {
        this.playerService = playerService;
        this.usersAccountService = usersAccountService;
        this.gameService = gameService;
    }
    
/////////////////////////////////////////////////////////////////////////////
    
    @GetMapping(value = {"/", "/panel"})
    public ModelAndView showPanelPage(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersAccount usersAccount = null;
        
        modelAndView.addObject("usersAccountName", auth.getName());
        
        // Adding the logged in UsersAccount to the Session,
        // for having access to the UsersAccount later, without a pulling it from a database again.
        if(session.getAttribute("loggedUsersAccount") == null) {
            usersAccount = usersAccountService.findUsersAccountByAccountName(auth.getName());
            session.setAttribute("loggedUsersAccount", usersAccount);
            
        } else {
            usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");   
        }
        // END
        
        Game currentGame = gameService.getOneByUsersAccountAndActive(usersAccount);
        List<Player> players = playerService.getAllPlayers(usersAccount);
        
        if(currentGame != null) {
            
            modelAndView.addObject("startGame", currentGame.getStart().toString());
            modelAndView.addObject("endGame", currentGame.getEnd().toString());
            
            // Checking if the current Game is over. 
            // If so, sets chooses the winner and current Game.Active to "false".
            if(gameService.compareEndGameDateWithToday(currentGame) == 1) {
                
                Player winner = playerService.comparePlayersScoresAndChooseTheWinner(players);
                
                currentGame.setActive(false);
                currentGame.setWinner(winner.getPlayerName());
                gameService.saveGame(currentGame);
                
                modelAndView.addObject("winner", winner.getPlayerName());
                modelAndView.addObject("winnerScore", winner.getScore());
                modelAndView.setViewName("winPage");
                
                // Game is over, so Scores are setting to zero.
                for(Player player : players) {
                    
                    player.setScore(0);
                    playerService.savePlayer(player);
                }
                
                return modelAndView;
            }
            // END
        }
        
        modelAndView.addObject("players", players);
        modelAndView.setViewName("mainPanel");
        
        return modelAndView;
    }
}