
package com.cp.web;

import com.cp.data.crud.BeanCrudPessoa;
import com.cp.data.crud.BeanCrudCidade;
import com.cp.data.model.Pessoa;
import com.cp.util.GenId;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author utfpr
 */
@Named("beanJFPessoa")
@RequestScoped
public class BeanJFPessoa {
    
    @Getter @Setter
    private int id;
    
    @Getter @Setter
    private String nome;

    @Getter @Setter
    private int cidade;
    
    @EJB 
    BeanCrudPessoa beanPessoa;

    @EJB
    BeanCrudCidade beanCidade;
    
    public void add(){
        if(id==0){
            FacesContext.getCurrentInstance().addMessage("ERRO",new FacesMessage("Erro: Código não pode ser zero."));
          //  return "";
        }
        if(beanPessoa.find(id)!=null){
            FacesContext.getCurrentInstance().addMessage("ERRO",new FacesMessage("Erro: Código existente."));
            //return "";
        }
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setId(id);
        beanPessoa.persist(p);
        p.setCidade(beanCidade.find(cidade));
        beanPessoa.merge(p);
       // return "";
    }
    
    public List<Pessoa> getAll(){
        return beanPessoa.getAll();
    }

    public void newID(){
        this.id=new GenId().getIdPrimo();
        this.nome=new GenId().getNome();
    }
}
