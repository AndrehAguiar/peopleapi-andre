package com.digitalinnovation.CadastroPessoas.service;

import com.digitalinnovation.CadastroPessoas.dto.request.PersonDTO;
import com.digitalinnovation.CadastroPessoas.dto.response.MessageResponseDTO;
import com.digitalinnovation.CadastroPessoas.entity.Person;
import com.digitalinnovation.CadastroPessoas.exception.PersonNotFoundException;
import com.digitalinnovation.CadastroPessoas.mapper.PersonMapper;
import com.digitalinnovation.CadastroPessoas.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savedToPerson = personRepository.save(personToSave);

        return createMessageResponse(savedToPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person personUpdated = personRepository.save(personToUpdate);

        return createMessageResponse(personUpdated.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
