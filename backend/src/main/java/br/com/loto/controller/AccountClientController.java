package br.com.loto.controller;

import br.com.loto.dto.PessoaClienteRequestDTO;
import br.com.loto.entity.Account;
import br.com.loto.service.PessoaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
public class AccountClientController {
    
    @Autowired
    private PessoaClienteService pessoaService;

    @PostMapping("/")
    public Account inserir(@RequestBody PessoaClienteRequestDTO pessoaClienteRequestDTO){
       // return pessoaService.registrar(pessoaClienteRequestDTO);
        return null;
    }

}