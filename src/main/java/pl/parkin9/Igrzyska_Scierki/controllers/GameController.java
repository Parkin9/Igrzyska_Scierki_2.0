/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Game;
import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.Task;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.GameService;
import pl.parkin9.Igrzyska_Scierki.services.PlayerService;
import pl.parkin9.Igrzyska_Scierki.services.TaskService;



/**
 * @author parkin9
 *
 */

@Controller
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;
    private final TaskService taskService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, TaskService taskService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.taskService = taskService;
    }
    
///////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/game")
    public ModelAndView showGamePanel(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        
        if(gameService.getOneByUsersAccountAndActive(usersAccount) == null) {

            modelAndView.addObject("game", new Game());
            modelAndView.setViewName("addGame");

        } else {
            
            List<Task> tasks = taskService.getAllTasks(usersAccount);
            List<Player> players = playerService.getAllPlayers(usersAccount);
            
            modelAndView.addObject("players", players);
            modelAndView.addObject("tasks", tasks);
            modelAndView.setViewName("gamePanel");
        }

        return modelAndView;
    }
}
