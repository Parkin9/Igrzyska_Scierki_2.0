/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
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
    
    @Autowired
    public PanelController(PlayerService playerService, UsersAccountService usersAccountService) {
        this.playerService = playerService;
        this.usersAccountService = usersAccountService;
    }
    
/////////////////////////////////////////////////////////////////////////////
    
    @GetMapping(value = {"/", "/panel"})
    public ModelAndView showPanelPage(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("mainPanel");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersAccount usersAccount = null;
        
        // Adding the logged in UsersAccount to the Session,
        // for having access to the UsersAccount later, without a pulling it from a database again.
        if(session.getAttribute("loggedUsersAccount") == null) {
            usersAccount = usersAccountService.findUsersAccountByAccountName(auth.getName());
            session.setAttribute("loggedUsersAccount", usersAccount);
            
        } else {
            // Getting the UsersAccount from the Session. It's needed to get Players from a database (below).
            usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        }
        
        // Getting a list of Players and putting it into a Model.
        modelAndView.addObject("players", playerService.getAllPlayers(usersAccount));
        modelAndView.addObject("name", auth.getName());
        
        return modelAndView;
    }
}
