package br.com.robson.github.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.robson.github.dto.RepositorySummary;
import br.com.robson.github.service.RepositoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping
@ApiOperation(value = "", authorizations = { @Authorization(value="Authorization") } )
public class GitHubController {
	
	@Autowired
	RepositoryService repositoryService;

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public List<RepositorySummary> index(@RequestBody String user) throws Exception {
		return repositoryService.listar(user);
	}
}
