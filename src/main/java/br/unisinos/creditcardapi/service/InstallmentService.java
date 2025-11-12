package br.unisinos.creditcardapi.service;

import br.unisinos.creditcardapi.dto.request.InstallmentRequest;
import br.unisinos.creditcardapi.dto.response.InstallmentResponse;

public interface InstallmentService {
    InstallmentResponse calculateInstallments(InstallmentRequest request);
}

