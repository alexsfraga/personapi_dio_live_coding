package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonDTO find(Long id){
        Person person = personRepository.findById(id).get();
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO create(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Person created with ID=" + savedPerson.getId())
                .build();
    }

    public MessageResponseDTO save(PersonDTO personDTO){
        Person personToSave = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .cpf(personDTO.getCpf())
                //.birthDate(personDTO.getBirthDate())
                .phones(personDTO.getPhones())
                .build();

        personToSave = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Person created with ID=" + personToSave.getId())
                .build();
    }

}
