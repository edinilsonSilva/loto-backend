package br.com.loto.api.controller;

import java.util.List;


import br.com.loto.service.PermissionServiceImpl;
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

import br.com.loto.domain.entity.Permission;

@RestController
@RequestMapping("/api/permissao")
@CrossOrigin
public class PermissionController {
    
    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @GetMapping("/")
    public List<Permission> buscarTodos(){
       return permissionServiceImpl.buscarTodos();
    }

    @PostMapping("/")
    public Permission inserir(@RequestBody Permission objeto){
        return permissionServiceImpl.inserir(objeto);
    }

    @PutMapping("/")
    public Permission alterar(@RequestBody Permission objeto){
        return permissionServiceImpl.alterar(objeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        permissionServiceImpl.excluir(id);
        return ResponseEntity.ok().build();
    }

}