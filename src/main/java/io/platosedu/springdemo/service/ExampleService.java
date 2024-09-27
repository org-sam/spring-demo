package io.platosedu.springdemo.service;

import io.platosedu.springdemo.model.Example;
import io.platosedu.springdemo.repository.ExampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExampleService {
    private final ExampleRepository repository;

    public ExampleService(ExampleRepository repository) {
        this.repository = repository;
    }

    public List<Example> getAll() {
    return repository.findAll();
    }

    public Example save(Example example) {
        log.info("Example received: " + example.toString());
        return repository.save(example);
    }

    public Example update(Long id, Example example) {
        validateExists(id);
        example.setId(id);
        return repository.save(example);
    }

    private void validateExists(Long id) {
        repository.findById(id).orElseThrow(()-> new RuntimeException("Exemplo não encontrado"));
    }

    public void deleteById(Long id) {
        validateExists(id);
        repository.deleteById(id);
    }
}
