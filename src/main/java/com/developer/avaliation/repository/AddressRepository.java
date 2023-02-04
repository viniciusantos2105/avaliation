package com.developer.avaliation.repository;

import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
