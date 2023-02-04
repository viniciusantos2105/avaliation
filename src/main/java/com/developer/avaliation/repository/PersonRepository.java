package com.developer.avaliation.repository;

import com.developer.avaliation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
