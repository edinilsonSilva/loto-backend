package br.com.loto.controller;

import java.util.HashMap;

import br.com.loto.security.JwtUtil;
import br.com.loto.service.PessoaGerenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loto.entity.Account;

@RestController
@RequestMapping("/api/pessoa-gerenciamento")
@CrossOrigin
public class AccountManagementController {
    
    @Autowired
    private PessoaGerenciamentoService pessoaGerenciamentoService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/senha-codigo")
    public String recuperarCodigo(@RequestBody Account account){
       return pessoaGerenciamentoService.solicitarCodigo(account.getUsername());
    }

    @PostMapping("/senha-alterar")
    public String alterarSenha(@RequestBody Account account){
       return pessoaGerenciamentoService.alterarSenha(account);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), "account.getSenha()"));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      Account autenticado = (Account) authentication.getPrincipal();
      String token = jwtUtil.gerarTokenUsername(autenticado);
      HashMap<String, Object> map = new HashMap<>();
      map.put("token", token);
      //map.put("permissoes", autenticado.getAuthorities());
      return ResponseEntity.ok(map);

    }



}