package br.unisinos.creditcardapi.dto.response;

import java.math.BigDecimal;

public class InstallmentResponse {
    private int numberOfInstallments;
    private BigDecimal installmentValue;
    private BigDecimal totalAmount;

    public InstallmentResponse(int numberOfInstallments, BigDecimal installmentValue, BigDecimal totalAmount) {
        this.numberOfInstallments = numberOfInstallments;
        this.installmentValue = installmentValue;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public BigDecimal getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(BigDecimal installmentValue) {
        this.installmentValue = installmentValue;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}

