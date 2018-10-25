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
public class PanelController {

    @GetMapping("/panel")
    public String showPanelPage() {
        
        return "mainPanel";
    }
}
