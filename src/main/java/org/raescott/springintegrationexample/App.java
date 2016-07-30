package org.raescott.springintegrationexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@SpringBootApplication
@ImportResource("classpath*:/integration-config.xml")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}