package br.com.loto.controller;

import java.util.List;


import br.com.loto.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loto.service.AccountServiceImpl;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin
public class AccountController {
    
    @Autowired
    private AccountServiceImpl pessoaService;

    @GetMapping("/")
    public List<Account> buscarTodos(){
       return pessoaService.buscarTodos();
    }

    @PostMapping("/")
    public Account inserir(@RequestBody Account objeto){
        return pessoaService.inserir(objeto);
    }

    @PutMapping("/")
    public Account alterar(@RequestBody Account objeto){
        return pessoaService.alterar(objeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        pessoaService.excluir(id);
        return ResponseEntity.ok().build();
    }

}