package br.unisinos.creditcardapi.controller;

import br.unisinos.creditcardapi.dto.request.InstallmentRequest;
import br.unisinos.creditcardapi.dto.response.InstallmentResponse;
import br.unisinos.creditcardapi.service.InstallmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<InstallmentResponse> calculate(@RequestBody InstallmentRequest request) {
        InstallmentResponse response = installmentService.calculateInstallments(request);
        return ResponseEntity.ok(response);
    }
}

