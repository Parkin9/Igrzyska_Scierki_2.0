/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage() {
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("usersAccount", new UsersAccount());
        
        return modelAndView;
    }
    
    @PostMapping("/registration")
    public ModelAndView registerAccount(@Valid UsersAccount usersAccount, BindingResult bindingResult) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
            
            modelAndView.setViewName("registration");
            return modelAndView;
            
        } else {
            
            modelAndView.setViewName("redirect:/");
            return modelAndView;
            
        }
    }
}
