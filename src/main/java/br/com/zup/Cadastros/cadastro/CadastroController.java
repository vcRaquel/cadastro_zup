package br.com.zup.Cadastros.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @PostMapping
    public Cadastro realizarCadastro(@RequestBody @Valid CadastroDTO cadastroDTO){
        return cadastroService.cadastrarNoBanco(cadastroDTO);
    }

    @GetMapping
    public List<CadastroDTOResumido> exibirCadastros(
            @RequestParam(required = false) Boolean moraSozinho,
            @RequestParam(required = false) Boolean temPet,
            @RequestParam(required = false) Integer idade) {
        List<CadastroDTOResumido> cadastroResumoDTOS = new ArrayList<>();
        for (Cadastro cadastro : cadastroService.exibirTodosCadastros(moraSozinho, temPet, idade)) {
            cadastroResumoDTOS.add(
                    new CadastroDTOResumido(cadastro.getCpf(), cadastro.getNome(), cadastro.getSobrenome()));
        }
        return cadastroResumoDTOS;
    }


    @DeleteMapping(path ={"/{cpf}"})
        public void delete(@PathVariable String cpf) {
            cadastroService.deletarCadastro(cpf);
        }

    //@GetMapping(path ={"/{id}"})

    /*
    todo  1 - OK - crie um metodo para cadastrar uma pessoa.
    Para um cadastro todo os campos são obrigatórios EXCETO o campo dataDoCadastro que deve ser preenchido pelo proprio
    sistema e o cliente não deve saber da existencia desse campo

    todo 2 - OK - Faça um metodo que retorna a lista inteira de cadastros ou filtrado por cadastros que moram sozinhos, que tem pet ou por idade.
    nessa lista deve ser retornado apenas os campos ID, NOME e SOBRENOME.

    todo 3 - faça um metodo para DELETAR um cadastro por id.

    todo 4 - faça um metodo que retorna TODOS os dados de um usuario pesquisado pelo ID.
    */

}
