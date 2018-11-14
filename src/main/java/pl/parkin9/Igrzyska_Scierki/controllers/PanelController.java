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
import pl.parkin9.Igrzyska_Scierki.services.UsersAccountService;

/**
 * @author parkin9
 *
 */

@Controller
public class PanelController {
    
    private final UsersAccountService usersAccountService;
    
    @Autowired
    public PanelController(UsersAccountService usersAccountService) {
        this.usersAccountService = usersAccountService;
    }
    
/////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/panel")
    public ModelAndView showPanelPage(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Adding a logged in UsersAccount to the Session,
        // for having access to UsersAccount later, without pulling it from a database again.
        if(session.getAttribute("loggedUsersAccount") == null) {
            UsersAccount loggedUsersAccount = usersAccountService.findUsersAccountByAccountName(auth.getName());
            session.setAttribute("loggedUsersAccount", loggedUsersAccount);
        }
        
        modelAndView.addObject("name", auth.getName());
        modelAndView.setViewName("mainPanel");
        
        return modelAndView;
    }
}
