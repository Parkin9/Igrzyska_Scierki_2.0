/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author parkin9
 *
 */
@Service
public class InputValidationService {

    public List<String> getAllMessagesFromBindingResultAsList(BindingResult bindingResult) {
        
        List<String> errorsMessages = new ArrayList<>();
        
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorMessage = fieldError.getDefaultMessage();
            errorsMessages.add(errorMessage); 
        }
        
        return errorsMessages;
    }
}
