package com.programming.techie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

class ContactManagerTest {

    private static ContactManager contactManager;

    @BeforeAll
    public static void setupAll(){
        System.out.println("Should print before all tests");
    }

    @BeforeEach
    public void setup(){
        System.out.println("Instantiating Contact Manager");
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should Create Contact")
    @RepeatedTest(5)
    public void shouldCreateContact() {
        contactManager.addContact("John", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
        .filter(contact -> contact.getFirstName().equals("John") &&
                contact.getLastName().equals("Doe") &&
                contact.getPhoneNumber().equals("0123456789"))
        .findAny()
        .isPresent());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Doe", "0123456789");
        });
    }
    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", null, "0123456789");
        });
    }
    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Should run only on Windows")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", "Doe", null);
        });
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should execute after each test");
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Should be executed at the end of the test");
    }




}