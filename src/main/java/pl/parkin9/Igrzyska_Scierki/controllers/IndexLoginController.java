/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author parkin9
 *
 */

@Controller
public class IndexLoginController {

    @GetMapping(value = {"/", "/welcome"})
    public ModelAndView showIndexPageWithLoginForm() {
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("indexLogin");
        
        return modelAndView;
    }
}
