package com.digitalinnovation.CadastroPessoas.repository;

import com.digitalinnovation.CadastroPessoas.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
