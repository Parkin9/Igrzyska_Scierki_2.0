/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author parkin9
 *
 */

@Controller
public class PanelController {

    @GetMapping("/panel")
    public ModelAndView showPanelPage() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("name", auth.getName());
        modelAndView.setViewName("mainPanel");
        
        return modelAndView;
    }
}
