package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    public static final String FIRSTNAME = "Billy";
    public static final String LASTNAME = "Kid";
    public static final long ID = 1L;

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testGetAllCustomers() {

        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDTOS.size());

    }

    @Test
    public void testGetById() {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findById(ID)).thenReturn(optionalCustomer);

        //when
        CustomerDTO customerDTO = customerService.getById(ID);

        //then
//        assertEquals(ID, customerDTO.getId());
//        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }

    @Test
    public void testCreateNewCustomer() {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("John");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl() );
    }

    @Test
    public void testSaveCustomerByDTO() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("John");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl() );

    }
}