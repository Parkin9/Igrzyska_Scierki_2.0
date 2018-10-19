/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author parkin9
 *
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String showLoginPage() {
        
        return "login";
    }
}
