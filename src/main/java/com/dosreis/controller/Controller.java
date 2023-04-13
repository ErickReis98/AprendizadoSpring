package com.dosreis.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Configuration
@Profile("developement") //a anotação @Profile distingue para qual perfil essa classe deve ser exibida
public class Controller {

	
	@GetMapping
	public String helloWorld() {
		return "<html><body style=\"background-color:blue\">Hello World man</body></html>";
	}
	
}
