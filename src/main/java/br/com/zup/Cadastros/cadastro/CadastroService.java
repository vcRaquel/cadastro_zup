package br.com.zup.Cadastros.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro salvarCadastro(Cadastro cadastro){
        //colocando a data atual no cadastro
        cadastro.setDataDoCadastro(LocalDate.now());
        //salvando a entidade no repository?
        return cadastroRepository.save(cadastro);
    }

//    public Cadastro cadastrarModel(CadastroDTO cadastroDTO) {//Cadastro cadastro
//        Cadastro cadastro = new Cadastro();
//        //calculando a data automaticamente
//        LocalDate dataAtual = LocalDate.now();
//
//        cadastro.setCpf(cadastroDTO.getCpf());
//        cadastro.setNome(cadastroDTO.getNome());
//        cadastro.setSobrenome(cadastroDTO.getSobrenome());
//        cadastro.setCidade(cadastroDTO.getCidade());
//        cadastro.setBairro(cadastroDTO.getBairro());
//        cadastro.setNomeDoParenteProximo(cadastroDTO.getNomeDoParenteProximo());
//        cadastro.setMoraSozinho(cadastroDTO.isMoraSozinho());
//        cadastro.setTemPet(cadastroDTO.isTemPet());
//        cadastro.setIdade(cadastroDTO.getIdade());
//
//        //adicionando a data calculada automaticamente
//        cadastro.setDataDoCadastro(dataAtual);
//
//        return cadastro;
//    }
//
//    public Cadastro cadastrarNoBanco(CadastroDTO cadastroDTO) {
//        return cadastroRepository.save(cadastrarModel(cadastroDTO));
//    }

    public List<Cadastro> exibirTodosCadastros(Boolean moraSozinho, Boolean temPet, Integer idade) {
        if (moraSozinho != null) {
            return cadastroRepository.findAllByMoraSozinho(moraSozinho);
        } else if (temPet != null) {
            return cadastroRepository.findAllByTemPet(temPet);
        } else if (idade != null) {
            return cadastroRepository.findAllByIdade(idade);
        }
        Iterable<Cadastro> simulacaos = cadastroRepository.findAll();
        return (List<Cadastro>) simulacaos;
    }

    public void deletarCadastro(String cpf) {
        cadastroRepository.deleteById(cpf);
    }

    public Cadastro buscaCadastroPorCpf(String cpf) {
        Optional<Cadastro> cadastro = cadastroRepository.findById(cpf);
        return cadastro.get();
    }


}
