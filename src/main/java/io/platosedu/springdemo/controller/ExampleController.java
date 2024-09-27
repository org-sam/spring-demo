package io.platosedu.springdemo.controller;

import io.platosedu.springdemo.controller.api.ExampleApi;
import io.platosedu.springdemo.model.Example;
import io.platosedu.springdemo.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController()
@Slf4j
public class ExampleController implements ExampleApi {
    private final ExampleService service;

    public ExampleController(ExampleService service) {
        this.service = service;
    }

    @Override
    public List<Example> getAll(HttpServletRequest request) {
        return service.getAll();
    }

    @Override
    public Example save(Example example) {
        return service.save(example);
    }

    @Override
    public Example update(Long id ,Example example) {
        return service.update(id, example);
    }

    @Override
    public void deleteById(Long id) {
         service.deleteById(id);
    }
}
