package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pessoa;
import com.example.demo.model.JsonResposta;

@Service
public class PessoaService {
  private List<Pessoa> pessoas = new ArrayList<>();

  public List<Pessoa> listar() {
    return this.pessoas;
  }

  public Pessoa buscarPorId(Long id) {
    return this.pessoas.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
  }

  public JsonResposta criar(Pessoa pessoa) {
    pessoa.setId(1L);
    OptionalLong maiorId = this.pessoas.stream().mapToLong(Pessoa::getId).max();
    if (maiorId.isPresent())
      pessoa.setId(maiorId.getAsLong() + 1L);
    this.pessoas.add(pessoa);
    return new JsonResposta("Ok",
        MessageFormat.format("Os dados da pessoa foi cadastrado (id: {0} - Nome: {1}) foram inseridos com sucesso!",
            pessoa.getId(), pessoa.getNome()));
  }

  public JsonResposta editar(Long id, Pessoa pessoa) {
    for (Pessoa key : this.pessoas) {
      if (key.getId().equals(id)) {
        key.setNome(pessoa.getNome());
        return new JsonResposta("Ok", MessageFormat.format("Os dados da pessoa foram atualizados (id: {0} - Nome: {1}) com sucesso!", key.getId(), key.getNome()));
      }
    }
    return new JsonResposta("Erro", MessageFormat.format("Os dados da pessoa (id: {0}) não foram atualizados", id));
  }

  public JsonResposta excluir(Long id) {
    Optional<Pessoa> pessoa = this.pessoas.stream().filter(c -> c.getId().equals(id)).findFirst();
    if (pessoa.isPresent()) {
      this.pessoas.removeIf(c -> c.getId().equals(id));
      return new JsonResposta("Ok",
          MessageFormat.format("Os dados da pessoa (Nome: {1}) foram excluidos!", pessoa.get().getNome()));
    }
    return new JsonResposta("Erro",
        MessageFormat.format("Os dados da pessoa (Nome: {1}) não foram excluidos!", pessoa.get().getNome()));
  }
}
