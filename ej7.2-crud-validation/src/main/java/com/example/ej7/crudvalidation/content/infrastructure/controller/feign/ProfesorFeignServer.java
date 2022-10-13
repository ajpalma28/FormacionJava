package com.example.ej7.crudvalidation.content.infrastructure.controller.feign;

import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profesor-service", url = "http://localhost:8081")
public interface ProfesorFeignServer {

    @GetMapping("/profesor/{id}")
    ResponseEntity<Object> getProfesorId(@PathVariable String id);

}
