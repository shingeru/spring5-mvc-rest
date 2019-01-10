package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "Billy";
    public static final String LASTNAME = "Kid";
    CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCustomerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setLastname(LASTNAME);
        customer.setFirstname(FIRSTNAME);

        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);

        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());


    }
}