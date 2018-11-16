/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import java.util.List;

import org.springframework.validation.BindingResult;

/**
 * @author parkin9
 *
 */
public interface InputValidationService {

    List<String> getAllMessagesFromBindingResultAsList(BindingResult bindingResult);

}
