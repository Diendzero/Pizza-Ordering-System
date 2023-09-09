package com.pizza.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pizza.models.Address;


public interface AddrRepo extends JpaRepository<Address, Integer> {

    Address findByIsDefaultTrue();

    List<Address> findByAccount(String phoneNum);

}
