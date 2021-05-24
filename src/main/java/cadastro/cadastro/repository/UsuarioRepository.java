/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.repository;

import cadastro.cadastro.model.UsuarioModel;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ISMsi
 */
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer>{

    @Override
    public Collection<UsuarioModel> findAll();
    
}
