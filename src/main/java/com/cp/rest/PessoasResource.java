package com.cp.rest;

import com.cp.data.crud.BeanCrudCidade;
import com.cp.data.crud.BeanCrudPessoa;
import com.cp.data.model.Cidade;
import com.cp.data.model.Pessoa;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


import java.util.ArrayList;
import java.util.List;

@Path("pessoas")
@Stateless
public class PessoasResource {


	@EJB
	BeanCrudPessoa beanCrudPessoa;

	@EJB
	BeanCrudCidade beanCrudCidade;

	@GET
	@Path("all")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JsonPessoa> getAll(){
		ArrayList<JsonPessoa> lpessoa = new ArrayList<>();
		for(Pessoa p : beanCrudPessoa.getAll()){
			JsonCidade Cid=null;
			if(p.getCidade()!=null){
				Cid = new JsonCidade(p.getCidade().getId(),p.getCidade().getNome());
			}
			lpessoa.add(new JsonPessoa(p.getId(),p.getNome(),Cid));
		}
		return lpessoa;
	}

	@GET
	@Path("cid")
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<JsonCidade> getCids(){
		ArrayList<JsonCidade> cidadel = new ArrayList<>();
		for(Cidade cid:beanCrudCidade.getAll()){
			cidadel.add(new JsonCidade(cid.getId(),cid.getNome()));
		}
		return cidadel;
	}

	public record JsonCidade(int id, String nome){}
	public record JsonPessoa(int id, String nome, JsonCidade cidade){}

}