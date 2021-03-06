package br.com.robson.github.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.robson.github.model.Repository;

public class RepositorySummary {

	private Boolean _private;
	private String created_at;
	private String description;
	private String full_name;
	private String language;
	private String name;
	private String owner;
	private String updated_at;
	
	public Boolean get_private() {
		return _private;
	}

	public void set_private(Boolean _private) {
		this._private = _private;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}


	public List<RepositorySummary> convert(String json) {
		ObjectMapper mapper = new ObjectMapper();
		List<RepositorySummary> list = new ArrayList<RepositorySummary>();
		try {
			JsonNode jsonNode = mapper.readTree(json.replace("\"private\"", "\"_private\""));
			Iterator<JsonNode> iterator = jsonNode.iterator();
			while (iterator.hasNext()) {
				RepositorySummary repositorySummary = new RepositorySummary();
				JsonNode marks = iterator.next();
				Repository repository = mapper.convertValue(marks, Repository.class);
				repositorySummary.set_private(repository.get_private());
				repositorySummary.setCreated_at(repository.getCreated_at());
				repositorySummary.setDescription(repository.getDescription());
				repositorySummary.setFull_name(repository.getFull_name());
				repositorySummary.setLanguage(repository.getLanguage());
				repositorySummary.setName(repository.getName());
				repositorySummary.setOwner(repository.getOwner().getLogin());
				repositorySummary.setUpdated_at(repository.getUpdated_at());
				list.add(repositorySummary);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
