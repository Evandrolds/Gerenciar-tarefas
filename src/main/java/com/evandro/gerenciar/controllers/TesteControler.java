package com.evandro.gerenciar.controllers;

import com.evandro.gerenciar.models.User;
import com.evandro.gerenciar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class TesteControler {
    @Autowired
    private UserService service;
    @PostMapping ("/cadastrar")
    private ResponseEntity<?> cadastrar(@RequestBody User user){
        return new ResponseEntity<>(service.Inserir(user),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> pesquisarUsuario(@PathVariable("id") long id){
        return new ResponseEntity<>(service.pesquisarPorId(id),HttpStatus.OK);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<User> atualizar(@PathVariable("id") long id,@RequestBody User user){
        return new ResponseEntity<>(service.atualizar(id,user),HttpStatus.CREATED);
    }
    @GetMapping("/buscar-todos")
    public ResponseEntity<List<User>> buscarTodos() throws FileNotFoundException {
        return new ResponseEntity<>(service.listar().stream().toList(),HttpStatus.OK);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuário(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteById(id),HttpStatus.MOVED_PERMANENTLY);
    }
}
