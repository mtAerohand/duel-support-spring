package com.mtaerohand.duelsupportspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 一般情報コントローラ
 */
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MainController {
    @GetMapping()
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Duel-Support API");
    }
}
