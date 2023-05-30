package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;

import peaksoft.exceptions.MyException;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AgencyRepository agencyRepository;

    public void saveCustomer(Customer customer, Long agencyId) {
        try {
            Agency agency = agencyRepository.findById(agencyId)
                    .orElseThrow(() -> new MyException("Agency with id: " + agencyId + "Not found"));
            agency.setCustomers(List.of(customer));
            customer.setAgencies(List.of(agency));
            customerRepository.save(customer);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }




    public Customer getCustomerById(Long id) {
        try {

            Customer customer = customerRepository.findById(id).orElseThrow(() -> new MyException("Customer with id: " + id + "not found"));
            return customer;

        } catch (MyException e) {
            System.out.println(e.getMessage());
        }return null;
    }

    public void deletedCustomerById(Long id) {
        try {
            boolean exists = customerRepository.existsById(id);
            if (!exists) {
                throw new MyException("Customer with id: " + id + " is not found");
            }
            customerRepository.deleteById(id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }



    public void updateCustomer(Long id, Customer customer) {
        Customer customer1 = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id: " + id + "not found"));
        customer1.setName(customer.getName());
        customer1.setSureName(customer.getSureName());
        customer1.setEmail(customer.getEmail());
        customer1.setGender(customer.getGender());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setDateOfBirth(customer.getDateOfBirth());
        customerRepository.save(customer1);
    }



}
