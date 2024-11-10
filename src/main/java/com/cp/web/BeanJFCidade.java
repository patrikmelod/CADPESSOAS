
package com.cp.web;

import com.cp.data.crud.BeanCrudCidade;
import com.cp.data.model.Cidade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author utfpr
 */
@Named("beanJFCidade")
@RequestScoped
public class BeanJFCidade {
    
    @Getter @Setter
    private int id;
    
    @Getter @Setter
    private String nome;

    
    @EJB
    BeanCrudCidade beanCidade;
    

    
    public List<Cidade> getAll(){
        return beanCidade.getAll();
    }
}
