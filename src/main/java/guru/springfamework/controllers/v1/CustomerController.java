package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerDTOList;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    ResponseEntity<CustomerDTOList> getCustomers() {

        return new ResponseEntity<CustomerDTOList>(new CustomerDTOList(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {

        return new ResponseEntity<CustomerDTO>(customerService.getById(Long.valueOf(id)), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
         return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }


    @PatchMapping("{id}")
    ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
