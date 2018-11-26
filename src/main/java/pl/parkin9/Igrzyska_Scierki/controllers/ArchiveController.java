/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.Game;
import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.GameService;

/**
 * @author parkin9
 *
 */
@Controller
public class ArchiveController {
    
    private final GameService gameService;
    
    public ArchiveController(GameService gameService) {
        this.gameService = gameService;
    }
    
//////////////////////////////////////////////////////////////////////////

    @GetMapping("/archive")
    public ModelAndView showArchive(HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        UsersAccount usersAccount = (UsersAccount)session.getAttribute("loggedUsersAccount");
        List<Game> archivedGames = gameService.getAllByUsersAccountAndNotActive(usersAccount);
        
        modelAndView.addObject("archivedGames", archivedGames);
        modelAndView.setViewName("archive");
        
        return modelAndView;
    }
}
