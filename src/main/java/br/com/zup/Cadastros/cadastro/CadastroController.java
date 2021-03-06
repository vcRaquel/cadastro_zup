package br.com.zup.Cadastros.cadastro;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarCadastro(@RequestBody CadastroDTO cadastroDTO){

       Cadastro cadastro = modelMapper.map(cadastroDTO,Cadastro.class);

        //chama o método da service que tanto adiciona a hora atualizada quanto salva no banco de dados
        cadastroService.salvarCadastro(cadastro);
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)//adicionado pelo Vini
//    public Cadastro realizarCadastro(@RequestBody @Valid CadastroDTO cadastroDTO){
//        return cadastroService.cadastrarNoBanco(cadastroDTO);
//    }

    @GetMapping
    public List<ResumoCadastroDTO> exibirCadastros(
            @RequestParam(required = false) Boolean moraSozinho,
            @RequestParam(required = false) Boolean temPet,
            @RequestParam(required = false) Integer idade) {
        List<ResumoCadastroDTO> cadastroResumoDTOS = new ArrayList<>();
        for (Cadastro cadastro : cadastroService.exibirTodosCadastros(moraSozinho, temPet, idade)) {
            cadastroResumoDTOS.add(
                    new ResumoCadastroDTO(cadastro.getCpf(), cadastro.getNome(), cadastro.getSobrenome()));
        }
        return cadastroResumoDTOS;
    }


    @DeleteMapping(path ={"/{cpf}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)// colocado pelo Vini, não existe corpo
        public void delete(@PathVariable String cpf) {
            cadastroService.deletarCadastro(cpf);
        }

    @GetMapping(path ={"/{cpf}"})
    public Cadastro buscaCadastroPeloId(@PathVariable String cpf) {
        return cadastroService.buscaCadastroPorCpf(cpf);
    }


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
