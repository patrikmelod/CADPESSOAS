


package com.cp.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

/**
 *
 * @author utfpr
 */
@Entity
@Data
public class Cidade implements Serializable {


    public Cidade(){
        //metodo exigido pela entity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    private String nome;


    @OneToMany(cascade=ALL, mappedBy="cidade")
   // @JsonBackReference
    private Set<Pessoa> pessoas;

}
