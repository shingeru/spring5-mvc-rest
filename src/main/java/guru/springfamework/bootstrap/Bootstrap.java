package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryLoad();

        customerLoad();
    }

    private void customerLoad() {
        Customer customer1 = new Customer();
        customer1.setFirstname("Billy");
        customer1.setLastname("Idol");

        Customer customer2 = new Customer();
        customer2.setFirstname("Joe");
        customer2.setLastname("Black");

        Customer customer3 = new Customer();
        customer3.setFirstname("Jimmy");
        customer3.setLastname("Page");

        Customer customer4 = new Customer();
        customer4.setFirstname("Carlon");
        customer4.setLastname("Santana");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);

        System.out.println("Data loaded - customers = " + customerRepository.count());
    }

    private void categoryLoad() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data Loaded - category = " + categoryRepository.count());
    }
}
