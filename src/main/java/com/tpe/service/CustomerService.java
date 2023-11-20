package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    //13-b-customer kaydetme
    public void saveCustomer(Customer customer) {

        boolean isExists = customerRepository.existsByEmail(customer.getEmail());
        if (isExists) {
            //13-d-custom exception fırlat
            throw new ConflictException("Customer already exists by email:" + customer.getEmail());
        }

        customerRepository.save(customer);
    }

    //14-b
    public List<Customer> getAll() {
        return customerRepository.findAll();//"FROM Customer"
    }


    public Customer getCustomerById(Long id){

        Customer customer = customerRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Customer is not found by id "+ id));
        return customer;
    }

    public CustomerDTO getCustomerDTO(Long identity) {
        Customer foundCustomer = getCustomerById(identity);

       CustomerDTO customerDTO = new CustomerDTO(foundCustomer);

       return customerDTO;
    }


    public void deleteCustomerById(Long id) {
        getCustomerById(id);
        customerRepository.deleteById(id);

    }

    public void updateCustomerById(Long id, CustomerDTO customerDTO) {//aaa@mail.com

        Customer foundCustomer=getCustomerById(id);

        boolean isExist=customerRepository.existsByEmail(customerDTO.getEmail());

        if (isExist && !customerDTO.getEmail().equals(foundCustomer.getEmail())){
            throw new ConflictException("Customer already exists by email:"+customerDTO.getEmail());
        }

        foundCustomer.setName(customerDTO.getName());
        foundCustomer.setLastName(customerDTO.getLastName());
        foundCustomer.setPhone(customerDTO.getPhone());
        foundCustomer.setEmail(customerDTO.getEmail());//tabloda bir customer email:aaa@mail.com

        customerRepository.save(foundCustomer);

    }

    public Page<Customer> getAllCustomerByPage(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public List<Customer> getCustomerByName(String name) {
            return customerRepository.findByName(name);
    }


    public List<Customer> getAllCustomerByFullName(String name, String lastName) {
        return customerRepository.findByNameAndLastName(name,lastName);
    }

    public List<Customer> getCustomerByNameLike(String word) {

     return customerRepository.findByNameLikeWord(word);

    }

}