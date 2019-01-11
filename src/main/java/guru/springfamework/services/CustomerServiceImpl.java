package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customerDTO.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getById(Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customerDTO.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);

//        return customerMapper.customerToCustomerDTO(customerRepository.findById(id).get());
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

//        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));


    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnCustomer = customerMapper.customerToCustomerDTO(savedCustomer);

        returnCustomer.setCustomerUrl("/api/v1/customers/" + returnCustomer.getId());

        return returnCustomer;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);

    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
                    if (customerDTO.getFirstname() != null) {
                        customer.setFirstname(customerDTO.getFirstname());
                    }
                    if (customerDTO.getLastname() != null) {
                        customer.setLastname(customerDTO.getLastname());
                    }
                    return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                }

        ).orElseThrow(RuntimeException::new);

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
