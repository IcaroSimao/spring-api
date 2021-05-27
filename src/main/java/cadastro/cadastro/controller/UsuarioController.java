/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.controller;

import cadastro.cadastro.model.UsuarioModel;
import cadastro.cadastro.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder pe;

    @Autowired
    private UsuarioRepository repositorio;

    @GetMapping(path = "api/usuario/{id}")
    public ResponseEntity getUsuarioById(@PathVariable("id") Integer id) {
        return repositorio.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "api/usuario/teste/{nome}")
    public UsuarioModel getUsuarioByNome(@PathVariable("nome") String nome) {
        List<UsuarioModel> todosUsuarios = getUsuarios();
        for (int i = 0; i < todosUsuarios.size(); i++) {
            UsuarioModel usuario = todosUsuarios.get(i);
            if (usuario.getUsuario().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean existsUsuarioByNome(String nome) {
        List<UsuarioModel> todosUsuarios = getUsuarios();
        for (int i = 0; i < todosUsuarios.size(); i++) {
            UsuarioModel usuario = todosUsuarios.get(i);
            if (usuario.getUsuario().equalsIgnoreCase(nome)) {
                return repositorio.existsById(usuario.getId());
            }
        }
        return false;
    }

    @GetMapping(path = "api/usuario")
    public List<UsuarioModel> getUsuarios() {
        return repositorio.findAll();
    }

    @PostMapping(path = "api/usuario/salvar")
    public String postUsuario(@RequestBody UsuarioModel data) {
        if (existsUsuarioByNome(data.getUsuario())) {
            return "Usuario já existente!";
        }
        // Necessário Encrypitar a senha antes de inserir no banco de dados
        String senha_segura = data.getSenha();
        data.setSenha(pe.encode(senha_segura));
        repositorio.save(data);
        return "Usuario cadastrado com sucesso";
    }

    @DeleteMapping(path = "api/usuario/{id}/apagar")
    public ResponseEntity deleteUsuario(@PathVariable("id") Integer id) {
        ResponseEntity usuario = getUsuarioById(id);
        repositorio.deleteById(id);
        return usuario;
    }
}
