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

        cadastro.setCpf(cadastroDTO.getCpf());
        cadastro.setNome(cadastroDTO.getNome());
        cadastro.setSobrenome(cadastroDTO.getSobrenome());
        cadastro.setCidade(cadastroDTO.getCidade());
        cadastro.setBairro(cadastroDTO.getBairro());
        cadastro.setNomeDoParenteProximo(cadastroDTO.getNomeDoParenteProximo());
        cadastro.setMoraSozinho(cadastroDTO.isMoraSozinho());
        cadastro.setTemPet(cadastroDTO.isTemPet());
        cadastro.setIdade(cadastroDTO.getIdade());

        //adicionando a data calculada automaticamente
        cadastro.setDataDoCadastro(dataAtual);

        return cadastro;
    }

    public Cadastro cadastrarNoBanco(CadastroDTO cadastroDTO) {
        return cadastroRepository.save(cadastrarModel(cadastroDTO));
    }

}
