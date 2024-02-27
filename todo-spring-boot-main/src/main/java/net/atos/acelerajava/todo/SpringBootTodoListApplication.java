package net.atos.acelerajava.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"net.atos.acelerajava.todo", "net.atos.acelerajava.todo.model"})
public class SpringBootTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTodoListApplication.class, args);
	}

}
