package com.digitalinnovation.CadastroPessoas.services;

import com.digitalinnovation.CadastroPessoas.dto.request.PersonDTO;
import com.digitalinnovation.CadastroPessoas.dto.response.MessageResponseDTO;
import com.digitalinnovation.CadastroPessoas.entity.Person;
import com.digitalinnovation.CadastroPessoas.repository.PersonRepository;
import com.digitalinnovation.CadastroPessoas.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.digitalinnovation.CadastroPessoas.Utils.PersonUtils.createFakeDTO;
import static com.digitalinnovation.CadastroPessoas.Utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedSuccessMessage(Long id) {
        return MessageResponseDTO.builder()
                .message("Created person with ID " + id)
                .build();
    }

}
