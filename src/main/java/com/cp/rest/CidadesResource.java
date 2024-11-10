package com.cp.rest;

import com.cp.data.crud.BeanCrudCidade;
import com.cp.data.model.Cidade;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("cidades")
@Stateless
public class CidadesResource {


	@EJB
	private BeanCrudCidade beanCrudCidade;


	@GET
	@Path("all")
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<JsonCidade> getCids(){
		ArrayList<JsonCidade> cidadel = new ArrayList<>();
		for(Cidade cid:beanCrudCidade.getAll()){
			cidadel.add(new JsonCidade(cid.getId(),cid.getNome()));
		}
		return cidadel;
	}

	@PUT
	@Path("add/{id}/{nome}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(@PathParam("id") int id,@PathParam("nome") String nome){
		Cidade cid = new Cidade();
		cid.setId(id);
		cid.setNome(nome);
		beanCrudCidade.persist(cid);
	}

	@PUT
	@Path("addjson")
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(JsonObject jsonData){
		Cidade cid = new Cidade();
		cid.setId(jsonData.getInt("id"));
		cid.setNome(jsonData.getString("nome"));
		beanCrudCidade.persist(cid);
	}

	@PUT
	@Path("addobj")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addObj(Cidade cid){
		beanCrudCidade.persist(cid);
	}


	public record JsonCidade(int id, String nome){}

}