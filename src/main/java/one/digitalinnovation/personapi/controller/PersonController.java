package one.digitalinnovation.personapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO creatPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.create(personDTO);
    }

    @GetMapping(value="/{id}")
    public PersonDTO getPerson(@PathVariable  Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @GetMapping(value = "/list")
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }


}
