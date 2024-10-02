package br.com.cadastro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Thiago");
        user.setEmail("thiago@example.com");
    }

    @Test
    void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Thiago", user.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("thiago@example.com", user.getEmail());
    }

    @Test
    void testSetName() {
        user.setName("Lucas");
        assertEquals("Lucas", user.getName());
    }
    @Test
    void testSetEmail() {
        user.setEmail("lucas@example.com");
        assertEquals("lucas@example.com", user.getEmail());
    }
}
