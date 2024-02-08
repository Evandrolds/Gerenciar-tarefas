package com.evandro.gerenciar.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteControler {
    @GetMapping
    private String mensagem(){
        return "Seja bem vindo!";
    }
    @GetMapping("/nome")
    private String mensagemNome(@RequestParam(name = "nome") String nome){
        return "Seja bem vindo " + nome;
    }
}
