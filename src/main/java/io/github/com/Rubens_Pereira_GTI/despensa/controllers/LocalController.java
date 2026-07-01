package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/local")
public class LocalController {

    private LocalService localService;

    public LocalController(LocalService localService){
        this.localService = localService;
    }

    @GetMapping
    public ResponseEntity<Local> findLocalById(@PathVariable Long id){
        return ResponseEntity.ok(localService.findById(id));
    }
}
