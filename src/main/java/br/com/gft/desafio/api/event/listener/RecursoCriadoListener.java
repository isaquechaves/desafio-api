package br.com.gft.desafio.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.desafio.api.event.RecursoCriadoEvent;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		
		Long id = recursoCriadoEvent.getId();
		
		adicionarHeaderLocaton(response, id);
	}

	private void adicionarHeaderLocaton(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand((id)).toUri();
			response.setHeader("Location", uri.toASCIIString());	
	}
	
}
