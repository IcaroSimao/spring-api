/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.controller;

import cadastro.cadastro.model.UsuarioModel;
import cadastro.cadastro.repository.UsuarioRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ISMsi
 */
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repositorio;

    @GetMapping(path = "api/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable("id") Integer id) {
        return repositorio.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping(path = "api/usuario")
    public Collection<UsuarioModel> getUsuarios() {
        return repositorio.findAll();
    }
    
    @PostMapping(path = "api/usuario/salvar")
    public UsuarioModel postUsuario(@RequestBody UsuarioModel data){
        return repositorio.save(data);
    }
    
    @DeleteMapping(path = "api/usuario/{id}/apagar")
    public ResponseEntity deleteUsuario(@PathVariable("id") Integer id){
        ResponseEntity usuario = getUsuario(id);
        repositorio.deleteById(id);
        return usuario;
    }
}
