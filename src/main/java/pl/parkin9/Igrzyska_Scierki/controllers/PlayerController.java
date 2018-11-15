/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.PlayerService;

/**
 * @author parkin9
 *
 */

@Controller
public class PlayerController {

    private final PlayerService playerService;
    
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
///////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/addPlayer")
    public ModelAndView showPlayerForm(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("addPlayer");
        
        modelAndView.addObject("player", new Player());
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        List<Player> players = playerService.getAllPlayers(usersAccount);
        modelAndView.addObject("players", players);
        
        return modelAndView;
    }
    
    @PostMapping("/addPlayer")
    public ModelAndView addNewPlayer(@Valid Player player, BindingResult bindingResult, HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("addPlayer");
            return modelAndView;
        }
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        
        // Checking if the provided Player, "under" the logged in UsersAccount, already exists in a database.
        List<Player> players = playerService.getAllPlayers(usersAccount);
        if(playerService.checkingIfPlayerAlreadyExists(players, player)) {
            
            modelAndView.addObject("errorAddPlayer", "Gracz o podanej nazwie już istnieje.");
            modelAndView.setViewName("addPlayer");
            return modelAndView;
        }
        // The end of a checking the existence.
        
        player.setUsersAccount(usersAccount);
        playerService.savePlayer(player);
        
        modelAndView.setViewName("redirect:/addPlayer");
                
        return modelAndView;
    }
    
    @GetMapping("/deletePlayer/{id}")
    public ModelAndView deletePlayer(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("redirect:/addPlayer");
        
        playerService.deletePlayer(id);
        
        return modelAndView;
    }
}
