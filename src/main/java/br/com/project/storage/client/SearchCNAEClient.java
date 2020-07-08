package br.com.project.storage.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.cnae.search.model.CNAE;
public interface SearchCNAEClient {
	
	static final String GET_CNAE_LIST_ENDPOINT_URL = "http://localhost:8090/CNAE/CNAE-classes";
	
	
	@GetMapping(value = "/getClasse", produces = MediaType.APPLICATION_JSON_VALUE)
	public CNAE getClasse();
}
