/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.service;

import cadastro.cadastro.controller.UsuarioController;
import cadastro.cadastro.model.UsuarioModel;
import cadastro.cadastro.repository.UsuarioRepository;
import cadastro.cadastro.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author ISMsi
 */
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsuarioController controller;
    
    // Busca do Usuario no SpringSecurty pelo username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Pegar Usuario do BD
        UsuarioModel usuario = controller.getUsuarioByNome(username);
        // Condição caso não encontre
        if (usuario == null)
            throw new UsernameNotFoundException(username);
        // Retorno caso encontre usuario
        // Retorna uma nova instancia de UserSS passando por parametros o usuário.
        return new UserSS(usuario.getId(), usuario.getUsuario(), usuario.getSenha(), usuario.getPerfis());
    }
    
}
