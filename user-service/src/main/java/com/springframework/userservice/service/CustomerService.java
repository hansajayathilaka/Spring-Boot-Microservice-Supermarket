package com.springframework.userservice.service;

import com.springframework.userservice.exception.DeletedUserException;
import com.springframework.userservice.exception.IdMismatchException;
import com.springframework.userservice.exception.InvalidQuantityException;
import com.springframework.userservice.exception.UserDoesNotExistException;
import com.springframework.userservice.model.entity.CartProduct;
import com.springframework.userservice.model.entity.Customer;
import com.springframework.userservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomerById(String id) {
        Customer customer = repository.findById(id).orElseThrow(UserDoesNotExistException::new);

        if (customer.isDeleted()) {
            throw new DeletedUserException();
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        var customers = repository.findAll();
        return customers.stream()
                .filter(customer -> !customer.isDeleted()).toList();
    }

    public Customer addCustomer(Customer customer) {
        customer.setCreated(new Date());
        return repository.save(customer);
    }

    public Customer updateCustomer(String customerId, Customer customer) {
        if (!customerId.equals(customer.getId())) {
            throw new IdMismatchException();
        }

        Customer oldCustomer = getCustomerById(customerId);
        oldCustomer.updateFields(customer);

        oldCustomer.setDateOfBirth(customer.getDateOfBirth() != null ? customer.getDateOfBirth() : oldCustomer.getDateOfBirth());
        oldCustomer.setAddress(customer.getAddress() != null ? customer.getAddress() : oldCustomer.getAddress());
        oldCustomer.setCart(customer.getCart() != null ? customer.getCart() : oldCustomer.getCart());
        oldCustomer.setProfilePicture(customer.getProfilePicture() != null ? customer.getProfilePicture() : oldCustomer.getProfilePicture());

        return repository.save(oldCustomer);
    }

    public Customer deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);

        if (!customer.isDeleted()) {
            customer.setDeleted(true);
            repository.save(customer);
        }

        return customer;
    }

    // Cart Handling

    public List<CartProduct> getCart(String customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getCart();
    }

    public CartProduct addToCart(String customerId, CartProduct product) {
        if(product.getQuantity() < 0) {
            throw new InvalidQuantityException();
        }

        Customer customer = getCustomerById(customerId);
        List<CartProduct> cart = customer.getCart();

        cart.add(product);

        return repository.save(customer).getCart().get(customer.getCart().size() - 1);
    }

    public List<CartProduct> updateCart(String customerId, List<CartProduct> cart) {
        Customer customer = getCustomerById(customerId);
        customer.setCart(cart);

        return repository.save(customer).getCart();
    }

    public CartProduct deleteFromCart(String customerId, CartProduct product) {
        Customer customer = getCustomerById(customerId);
        List<CartProduct> cart = customer.getCart();

        cart.removeIf(item -> item.getId().equals(product.getId()));
        repository.save(customer);

        return product;
    }

    public void clearCart(String customerId) {
        Customer customer = getCustomerById(customerId);
        customer.setCart(List.of());
        repository.save(customer);
    }
}
