/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.parkin9.Igrzyska_Scierki.models.Player;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.InputValidationService;
import pl.parkin9.Igrzyska_Scierki.services.PlayerService;

/**
 * @author parkin9
 *
 */

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final InputValidationService inputValidationService;
    private List<Player> players = null;
    
    @Autowired
    public PlayerController(PlayerService playerService, InputValidationService inputValidationService) {
        this.playerService = playerService;
        this.inputValidationService = inputValidationService;
    }
    
///////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/addPlayer")
    public ModelAndView showPlayerForm(@ModelAttribute(value="validErrorsMessages") String validErrorsMessages, 
                                       @RequestParam(value="inputError", required=false) String inputErrorParam, 
                                                                                         HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("addPlayer");
        
        modelAndView.addObject("player", new Player());
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        players = playerService.getAllPlayers(usersAccount);
        modelAndView.addObject("players", players);
        
        if(inputErrorParam != null) {
            
            switch(inputErrorParam) {
            
            case "playerAlreadyExists":
                modelAndView.addObject("playerExists", "*Gracz o podanej nazwie już istnieje.");
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
                break;
                
            case "validationError":
                List<String> errorsMessages = Arrays.asList(validErrorsMessages.split(","));
                modelAndView.addObject("errorsMessages", errorsMessages);
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
                break;
            }
        }
        
        return modelAndView;
    }
    
    @PostMapping("/addPlayer")
    public ModelAndView addNewPlayer(@Valid Player player, 
                                            BindingResult bindingResult, 
                                            HttpSession session, 
                                            RedirectAttributes redirectAttributes) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        // Checking if a Hibernate Validation returns any errors.
        // If yes, pulls they from BindingResult and redirects to GET/addPlayer.
        if(bindingResult.hasErrors()) {
            List<String> errorsMessages = inputValidationService.getAllMessagesFromBindingResultAsList(bindingResult);
            redirectAttributes.addFlashAttribute("validErrorsMessages", errorsMessages);
            modelAndView.setViewName("redirect:/addPlayer?inputError=validationError");
            
            return modelAndView;
        }// END
        
        // Checking if the provided Player, with a concrete PlayerName, already exists in a database.
        if(playerService.checkingIfPlayerNameAlreadyExists(players, player)) {
            modelAndView.setViewName("redirect:/addPlayer?inputError=playerAlreadyExists");
            
            return modelAndView;
        }// END
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
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
