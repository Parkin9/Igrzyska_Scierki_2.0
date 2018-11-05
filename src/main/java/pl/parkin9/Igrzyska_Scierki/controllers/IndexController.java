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
public class IndexController {

    @GetMapping("/")
    public String showIndex() {
        
        return "index";
    }
    
    // TODO: @PostMapping
}
