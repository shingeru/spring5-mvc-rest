package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRSTNAME = "Adam";
    public static final String LASTNAME = "Sandler";
    public static final long ID = 1L;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();


    }

    @Test
    public void testGetCustomers() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Vincent");
        customer2.setLastname("Vega");

        List<CustomerDTO> customerDTOS = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));


    }

    @Test
    public void getCustomerById() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);
        customer1.setId(ID);

        when(customerService.getById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));


    }

    @Test
    public void testCreateNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Fred");
        customerDTO.setLastname("Flinston");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastname(customerDTO.getLastname());
        returnDTO.setFirstname(customerDTO.getFirstname());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.lastname", equalTo(customerDTO.getLastname())))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Fred");
        customerDTO.setLastname("Flinston");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastname(customerDTO.getLastname());
        returnDTO.setFirstname(customerDTO.getFirstname());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(1L, customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.lastname", equalTo(customerDTO.getLastname())))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));


    }

    @Test
    public void testPatchCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Fred");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastname(customerDTO.getLastname());
        returnDTO.setFirstname(customerDTO.getFirstname());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.patchCustomer(1L, customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(patch("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
//                .andExpect(jsonPath("$.lastame", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));


    }

    @Test
    public void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

}