/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.controller;

import cadastro.cadastro.security.JWTUtil;
import cadastro.cadastro.security.UserSS;
import cadastro.cadastro.service.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ISMsi
 */
@RestController
public class AuthController {
    @Autowired
    private JWTUtil jwtUtil; 
    
    @PostMapping(path = "/auth/refresh-token")
    public ResponseEntity refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        // Novo token com usuário logado
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
