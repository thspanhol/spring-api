package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Método para cadastrar pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj){

        if (obj.getNome().equals("")) {
            mensagem.setMensagem("O nome precisa ser informado.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // Método para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Método para selecionar pessoas pelo código
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){

        if (acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("Pessoa não encontrada.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);    
        } else {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
        
    }
    
}
