package io.platosedu.springdemo.controller.api;

import io.platosedu.springdemo.model.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExampleApi {
    @GetMapping("/example")
    public List<Example> getAll(HttpServletRequest request);

    @PostMapping("/example")
    public Example save(@RequestBody Example example);

    @PutMapping("/example/{id}")
    public Example update(@PathVariable Long id, @RequestBody Example example);

    @DeleteMapping("/example/{id}")
    public void deleteById(@PathVariable Long id);
}
