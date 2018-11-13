/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.services.PlayerService;
import pl.parkin9.Igrzyska_Scierki.services.UsersAccountService;

/**
 * @author parkin9
 *
 */

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final UsersAccountService usersAccountService; 
    
    @Autowired
    public PlayerController(PlayerService playerService, UsersAccountService usersAccountService) {
        this.playerService = playerService;
        this.usersAccountService = usersAccountService;
    }
    
////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/addPlayer")
    public ModelAndView showPlayerForm() {
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPlayer");
        modelAndView.addObject("player", new Player());
        return modelAndView;
    }
    
    @PostMapping("/addPlayer")
    public ModelAndView addNewPlayer(@Valid Player player, BindingResult bindingResult) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
            
            modelAndView.setViewName("addPlayer");
            return modelAndView;
        }
        
//        if(playerService.findPlayerByPlayerName(player.getPlayerName()) == null) {
//            
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            
//            // Setting Player.UsersAccount (a relation in database),
//            // and save Player in a database.
            player.setUsersAccount(usersAccountService.findUsersAccountByAccountName(auth.getName()));
            playerService.savePlayer(player);
//            
//            modelAndView.setViewName("redirect:/addPlayer");
//            return modelAndView;
//            
//        } else {
//            
//            modelAndView.addObject("errorAddPlayer", "Gracz o podanej nazwie już istnieje");
        System.out.println(playerService.checkIfPlayerExists(usersAccountService.findUsersAccountByAccountName(auth.getName()), player.getPlayerName()));
            modelAndView.setViewName("addPlayer");
            return modelAndView;
//        }
    }
}
