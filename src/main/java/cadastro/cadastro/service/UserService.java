/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.service;

import cadastro.cadastro.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ISMsi
 */
public class UserService {

    public static UserSS authenticated() {
        try {
            // Retorna o usuário logado
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
