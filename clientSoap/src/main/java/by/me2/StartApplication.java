package by.me2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.me2.configuration.SoapClientConfiguration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class StartApplication {

	public static void main(String[] args) {

		SpringApplication.run(new Object[]{StartApplication.class, SoapClientConfiguration.class}, args);
	}
}
