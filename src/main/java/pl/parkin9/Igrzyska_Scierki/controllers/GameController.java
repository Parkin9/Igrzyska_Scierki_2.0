/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView showGamePanel(@RequestParam(value="inputError", required=false) String inputErrorParam, 
                                                                                        HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        
        if(gameService.getOneByUsersAccountAndActive(usersAccount) == null) {
            
            modelAndView.addObject("game", new Game());
            modelAndView.setViewName("createGame");
            
            if((inputErrorParam != null) && (inputErrorParam.equals("wrongEndGameDate"))) {
                modelAndView.addObject("endGameError", "*Data zakończenia gry musi znajdować się w przyszłości.");
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            }

        } else {
            
            List<Task> tasks = taskService.getAllTasks(usersAccount);
            List<Player> players = playerService.getAllPlayers(usersAccount);
            
            modelAndView.addObject("players", players);
            modelAndView.addObject("tasks", tasks);
            modelAndView.setViewName("updateGame");
        }

        return modelAndView;
    }
    
    @PostMapping("/newGame")
    public ModelAndView addNewGame(@ModelAttribute Game game, 
                                                   HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        // Checking if the chosen Game.End date is after the current date.
        Integer endGameValue = gameService.compareEndGameDateWithToday(game); 
        if((endGameValue == 0) || (endGameValue == 1)) {
            
            modelAndView.setViewName("redirect:/game?inputError=wrongEndGameDate");
            return modelAndView;
        }// END Validating
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        
        game.setUsersAccount(usersAccount);
        gameService.saveGame(game);
        
        modelAndView.setViewName("redirect:/panel");
        
        return modelAndView;
    }
    
    @PostMapping("/updateGame")
    public ModelAndView updateCurrentGame(HttpServletRequest request, HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        // Getting Player which did Tasks.
        Player player = playerService.getOneById(Long.valueOf(request.getParameter("whichPlayerDo")));
        
        // Getting Tasks which are done.
        String[] tasksIDsStr = request.getParameterValues("whichTasksIsDone");
        
        Long[] tasksIDs = new Long[tasksIDsStr.length];
        for(int i = 0; i < tasksIDsStr.length; i++) {
            tasksIDs[i] = Long.valueOf(tasksIDsStr[i]);
        }
        
        List<Task> tasks = new ArrayList<>();
        for(Long taskId : tasksIDs) {
            tasks.add(taskService.getOneById(taskId));
        }// END Getting Tasks

        // Updating Player.Score for Tasks which he did.
        for(Task task : tasks) {
            player.setScore((player.getScore()) + (task.getPointsValue()));
        }// END Updating

        playerService.savePlayer(player);
        modelAndView.setViewName("redirect:/panel");

        return modelAndView;
    }
}