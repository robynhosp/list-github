package br.com.robson.github.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.robson.github.dto.RepositorySummary;

@Service
public class RepositoryService {

	private RepositorySummary repositoryDto;
	
	private RestTemplate restTemplate;
	
	public RepositoryService(){
		this.restTemplate = new RestTemplate();
		this.repositoryDto = new RepositorySummary();
	}
	
	public UriComponents uri(String username) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("&page", "1");
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.github.com")
				.path("/users/"+username+"/repos").queryParams(params).build();
		return uriComponents;
	}

	public List<RepositorySummary> listar(String username) throws Exception {
		UriComponents uri = uri(username.replace("\"", ""));
		
		ResponseEntity<String> repositoryResponseEntity = restTemplate.getForEntity(uri.toUriString(), String.class);
		List<RepositorySummary> items = repositoryDto.convert(repositoryResponseEntity.getBody());
		return items;
	}
	
}
