package org.example;

import org.example.Helpers.AppHelperCustomer;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Input;
import org.example.model.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class AppHelperCustomerTest {

    private AppHelperCustomer appHelperCustomer;
    private FileRepository<Customer> customerRepositoryMock;
    private Input inputProviderMock;

    @Before
    public void setUp() {
        customerRepositoryMock = mock(FileRepository.class);
        inputProviderMock = mock(Input.class);

        appHelperCustomer = new AppHelperCustomer(customerRepositoryMock, inputProviderMock);
    }

    @Test
    public void testCreateSuccess() {
        // Arrange
        when(inputProviderMock.getInput()).thenReturn("John", "Boos", "115623456527890");

        // Act
        Customer customer = appHelperCustomer.create();

        // Assert
        assertNotNull(customer);
        assertEquals("John", customer.getName());
        assertEquals("Boos", customer.getSurname());
        assertEquals("115623456527890", customer.getPhone());
    }

    @Test
    public void testCreateWithEmptyInput() {
        // Arrange
        when(inputProviderMock.getInput()).thenReturn("", "", "");

        // Act
        Customer customer = appHelperCustomer.create();

        // Assert
        assertNotNull(customer);
        assertEquals("", customer.getName());
        assertEquals("", customer.getSurname());
        assertEquals("", customer.getPhone());
    }

    @Test
    public void testPrintListWithCustomers() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Maks", "Ali", "523616");
        Customer customer2 = new Customer("Bob", "Muhamed", "2915325");
        customers.add(customer1);
        customers.add(customer2);

        // Act
        appHelperCustomer.printList(customers);

        // Assert
        // Optionally, capture and assert the console output
    }

    @Test
    public void testPrintListEmpty() {
        // Arrange
        List<Customer> customers = new ArrayList<>();

        // Act
        appHelperCustomer.printList(customers);

        // Assert
        // Optionally, capture and assert the console output
    }

    @Test
    public void testGetRepository() {
        // Act
        FileRepository<Customer> repository = appHelperCustomer.getRepository();

        // Assert
        assertEquals(customerRepositoryMock, repository);
    }
}
