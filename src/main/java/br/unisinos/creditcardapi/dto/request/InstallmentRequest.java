package br.unisinos.creditcardapi.dto.request;

import br.unisinos.creditcardapi.domain.CreditCardBrand;

import java.math.BigDecimal;

public class InstallmentRequest {
    private BigDecimal purchaseAmount;
    private int numberOfInstallments;
    private CreditCardBrand creditCardBrand;

    // Getters and Setters
    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public CreditCardBrand getCreditCardBrand() {
        return creditCardBrand;
    }

    public void setCreditCardBrand(CreditCardBrand creditCardBrand) {
        this.creditCardBrand = creditCardBrand;
    }
}

