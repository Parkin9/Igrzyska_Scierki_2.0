/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Player;
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
        modelAndView.setViewName("redirect:/addPlayer");
        
        if(!bindingResult.hasErrors()) {
            playerService.savePlayer(player);
        }
        
        return modelAndView;
    }
}
