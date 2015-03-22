package by.me2.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import by.me2.client.CityClient;

@Configuration
@EnableAutoConfiguration
public class SoapClientConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("city.wsdl");
		return marshaller;
	}

	@Bean
	public CityClient cityClient(Jaxb2Marshaller marshaller) {
		CityClient client = new CityClient();
		client.setDefaultUri("http://188.166.5.154:8080/micro/service/service.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
