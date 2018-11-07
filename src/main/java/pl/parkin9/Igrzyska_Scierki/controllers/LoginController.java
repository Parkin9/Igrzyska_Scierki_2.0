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
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public ModelAndView showLoginFormOnIndexPage() {
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        
        return modelAndView;
    }
    
//    @PostMapping("/login")
//    public ModelAndView showMainPanel(@Valid UsersAccount usersAccount, BindingResult bindingResult) {
//        
//        ModelAndView modelAndView = new ModelAndView();
//        
//        if(bindingResult.hasErrors()) {
//            
//            modelAndView.setViewName("login");
//            return modelAndView;    
//        }
//        
//        usersAccount.setEnable(true);
//        
//        modelAndView.setViewName("mainPanel");
//        
//        return modelAndView;
//    }
}
