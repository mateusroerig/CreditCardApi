package br.unisinos.creditcardapi.service.impl;

import br.unisinos.creditcardapi.dto.request.InstallmentRequest;
import br.unisinos.creditcardapi.dto.response.InstallmentResponse;
import br.unisinos.creditcardapi.service.InstallmentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class InstallmentServiceImpl implements InstallmentService {

    private static final BigDecimal MIN_PURCHASE_AMOUNT = new BigDecimal("10.00");
    private static final Map<Integer, BigDecimal> INTEREST_RATES = Map.of(
            1, BigDecimal.ZERO,
            2, new BigDecimal("0.0299"),
            3, new BigDecimal("0.0401"),
            4, new BigDecimal("0.0502"),
            5, new BigDecimal("0.0605"),
            6, new BigDecimal("0.0708")
    );

    @Override
    public InstallmentResponse calculateInstallments(InstallmentRequest request) {
        validateRequest(request);

        int numberOfInstallments = request.getNumberOfInstallments();
        BigDecimal purchaseAmount = request.getPurchaseAmount();

        BigDecimal interestRate = INTEREST_RATES.get(numberOfInstallments);
        BigDecimal totalAmount = purchaseAmount.multiply(BigDecimal.ONE.add(interestRate));
        BigDecimal installmentValue = totalAmount.divide(new BigDecimal(numberOfInstallments), 2, RoundingMode.HALF_UP);

        return new InstallmentResponse(numberOfInstallments, installmentValue, totalAmount);
    }

    private void validateRequest(InstallmentRequest request) {
        if (request.getPurchaseAmount().compareTo(MIN_PURCHASE_AMOUNT) < 0) {
            throw new IllegalArgumentException("Purchase amount must be at least " + MIN_PURCHASE_AMOUNT);
        }
        if (!INTEREST_RATES.containsKey(request.getNumberOfInstallments())) {
            throw new IllegalArgumentException("Invalid number of installments. Allowed values are: " + INTEREST_RATES.keySet());
        }
    }
}

