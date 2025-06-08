package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Pessoa;
import com.example.demo.model.JsonResposta;
import com.example.demo.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
  @Autowired
  private PessoaService service;

  @GetMapping("/todos")
  public List<Pessoa> listar() {
    return this.service.listar();
  }

  @GetMapping("/{id}")
  public Pessoa buscarPorId(@PathVariable Long id) {
    return this.service.buscarPorId(id);
  }

  @PostMapping
  public JsonResposta criar(@RequestBody Pessoa pessoa) {
    return this.service.criar(pessoa);
  }

  @PutMapping("/{id}")
  public JsonResposta editar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
    return this.service.editar(id, pessoa);
  }

  @DeleteMapping("/{id}")
  public JsonResposta excluir(@PathVariable Long id) {
    return this.service.excluir(id);
  }
}
