package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryLoad();

        customerLoad();

        vendorLoad();

    }

    private void vendorLoad() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Western Tasty Fruits Ltd.");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Exotic Fruits Company");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Home Fruits");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Fun Fresh Fruits Ltd.");

        Vendor vendor5 = new Vendor();
        vendor5.setName("Nuts for Nuts Company");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
        vendorRepository.save(vendor5);

        System.out.println("Data loaded - vendors = " + vendorRepository.count());
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
