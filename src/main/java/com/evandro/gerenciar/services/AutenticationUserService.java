package com.evandro.gerenciar.services;

import com.evandro.gerenciar.models.User;
import com.evandro.gerenciar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AutenticationUserService implements UserDetailsService{

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String  username){
        User user = repository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("Usuário "+ username+ "não foi encontrado!"));
       List<SimpleGrantedAuthority> roles = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).toList();
       return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }

}
