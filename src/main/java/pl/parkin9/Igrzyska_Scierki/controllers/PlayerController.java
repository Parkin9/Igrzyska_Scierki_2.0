/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
        Set<Player> playersSet = playerService.findAllPlayers(usersAccount);
        modelAndView.addObject("playersSet", playersSet);
        
        return modelAndView;
    }
    
    @PostMapping("/addPlayer")
    public ModelAndView addNewPlayer(@Valid Player player, BindingResult bindingResult, HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("addPlayer");
            
        } else {
            UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
            player.setUsersAccount(usersAccount);
            playerService.savePlayer(player);
            
            modelAndView.setViewName("redirect:/addPlayer");
        }
        
        return modelAndView;
    }
}
