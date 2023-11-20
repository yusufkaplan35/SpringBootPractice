package com.tpe.repository;

import com.tpe.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//optional
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //13-c
    boolean existsByEmail(String email);//Spring tarafından derleme aşamasında implemente edilir.

    List<Customer> findByName(String name);

    List<Customer> findByNameAndLastName(String name, String lastName);


    //jpql
    @Query("")
    List<Customer> findByNameLikeWord(String word);

}