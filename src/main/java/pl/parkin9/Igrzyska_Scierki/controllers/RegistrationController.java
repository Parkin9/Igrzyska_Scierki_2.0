/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.services.UsersAccountService;

/**
 * @author parkin9
 *
 */

@Controller
public class RegistrationController {

    private final UsersAccountService usersAccountService;
    
    @Autowired
    public RegistrationController(UsersAccountService usersAccountService) {
        this.usersAccountService = usersAccountService;
    }
    
///////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/registration")
    public ModelAndView showRegistrationPage() {
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usersAccount", new UsersAccount());
        
        return modelAndView;
    }
    
    @PostMapping("/registration")
    public ModelAndView registerAccount(@Valid UsersAccount usersAccount, BindingResult bindingResult) {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            return modelAndView;
        }
        
        // Checking if provided Password matches to PasswordConfirm.
        if(usersAccountService.comparePasswordWithPasswordConfirm(usersAccount)){
            
            // Checking if UsersAccount already exists in a database.
            // If no (it's NULL), encoding Password and saving UsersAccount in a database.
            if(usersAccountService.findUsersAccountByAccountName(usersAccount.getAccountName()) == null) {
                
                usersAccountService.encodePassword(usersAccount);
                usersAccountService.saveUsersAccount(usersAccount);
                
                modelAndView.setViewName("redirect:/login");
                return modelAndView;
                
            } else {
                
                modelAndView.addObject("errorRegistration", "*Konto o podanej nazwie już istnieje.");
                modelAndView.setViewName("registration");
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
                return modelAndView;
            }// END
            
        } else {
            
            modelAndView.addObject("errorRegistration", "*Błąd podczas potwierdzania hasła.");
            modelAndView.setViewName("registration");
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            return modelAndView;
        }// END
    }
}
