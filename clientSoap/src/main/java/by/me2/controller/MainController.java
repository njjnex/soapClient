package by.me2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.me2.client.CityClient;
import by.me2.model.Credentials;

@Controller
public class MainController {

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping("/")
	public String main() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody Credentials cre) {
		
		CityClient client = appContext.getBean(CityClient.class);
		return client.auth(cre.getEmail(), cre.getPassword()).getSession();
	}

	@RequestMapping(value = "/citiesList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCityList(@RequestBody String session) {

		CityClient client = appContext.getBean(CityClient.class);
		return client.getCity(session).getCity();

	}
}
