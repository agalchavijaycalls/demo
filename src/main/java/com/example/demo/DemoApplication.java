package com.example.demo;

import com.example.demo.dto.Todo;
import com.example.demo.rest.TodosController;
import com.example.demo.dao.BookmarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private TodosController todosController;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BookmarkRepository bookmarkRepository) {
        return (evt) -> {
            logger.debug("Initializing database");
            List<String> strings1 = Arrays.asList("abc,bcd,cde".split(","));
            strings1.stream().forEach((String str) -> {
                Todo todo = new Todo();
                todo.setCompleted(false);
                todo.setOrder(2);
                todo.setTitle(str);
                todosController.saveTodo(todo);
            });
            logger.debug("Initialization database : Done");
        };
    }
}
