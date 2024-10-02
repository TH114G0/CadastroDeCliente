package br.com.cadastro.service;

import br.com.cadastro.model.User;
import br.com.cadastro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean registerUsers(String name, String email) {
        User user = new User();
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vázio");
        }
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vázio");
        }
        user.setName(name);
        user.setEmail(email);
        try {
            userRepository.save(user);
            return true;
        }catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    public List<User> usersList() {
        List<User> userList;
        try {
            userList = userRepository.findAll();
        } catch (Exception  ex) {
            throw new RuntimeException("Erro ao buscar usuários", ex);
        }
        return userList;
    }
    public Optional<User> findById(Long id) {
        Optional<User> user;
        try {
            user = userRepository.findById(id);
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar usuários por ID", ex);
        }
        return user;
    }
    public boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
