package com.evandro.gerenciar.services;

import com.evandro.gerenciar.models.User;
import com.evandro.gerenciar.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void Inserir(User user){
        if(user.getUsername()==null){
            throw new RuntimeException("O usuário precisa de um nome!");
        }
        repository.save(user);
    }
    public List<User> listar() throws FileNotFoundException {
        List<User> users = repository.findAll();
        if(users.isEmpty()){
            throw new FileNotFoundException("Lista de usuário vazia!");
        }
        return users;
    }
    public User pesquisarPorId(Long id){
        User user = repository.findById(id).orElseThrow(() ->new EntityNotFoundException("Usuário não encontrado!"));
       return user;
    }
    public void atualizar(Long id, User user){
        User usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
        BeanUtils.copyProperties(user,usuario);
        repository.save(usuario);
    }

}
