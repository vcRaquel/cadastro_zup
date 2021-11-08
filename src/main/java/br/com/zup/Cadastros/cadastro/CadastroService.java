package br.com.zup.Cadastros.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro cadastrarModel(CadastroDTO cadastroDTO) {
        Cadastro cadastro = new Cadastro();
        //calculando a data automaticamente
        LocalDate dataAtual = LocalDate.now();

        cadastro.setBairro(cadastroDTO.getBairro());
        cadastro.setCidade(cadastroDTO.getCidade());
        cadastro.setCpf(cadastroDTO.getCpf());
        cadastro.setIdade(cadastroDTO.getIdade());
        cadastro.setNome(cadastroDTO.getNome());
        cadastro.setMoraSozinho(cadastroDTO.isMoraSozinho());
        cadastro.setTemPet(cadastroDTO.isTemPet());
        cadastro.setNomeDoParenteProximo(cadastroDTO.getNomeDoParenteProximo());
        cadastro.setSobrenome(cadastroDTO.getSobrenome());
        //adicionando a data calculada automaticamente
        cadastro.setDataDoCadastro(dataAtual);

        return cadastro;
    }
}
