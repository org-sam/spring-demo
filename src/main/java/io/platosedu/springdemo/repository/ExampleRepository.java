package io.platosedu.springdemo.repository;

import io.platosedu.springdemo.model.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository  extends JpaRepository<Example, Long> {
}
