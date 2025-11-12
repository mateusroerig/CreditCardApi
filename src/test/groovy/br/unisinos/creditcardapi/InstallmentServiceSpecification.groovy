package br.unisinos.creditcardapi

import br.unisinos.creditcardapi.domain.CreditCardBrand
import br.unisinos.creditcardapi.dto.request.InstallmentRequest
import br.unisinos.creditcardapi.service.impl.InstallmentServiceImpl
import spock.lang.Specification
import spock.lang.Unroll

import java.math.RoundingMode

class InstallmentServiceSpecification extends Specification {

    def installmentService = new InstallmentServiceImpl()

    def "should calculate installments without interest"() {
        given:
        def request = new InstallmentRequest(
                purchaseAmount: new BigDecimal("100.00"),
                numberOfInstallments: 1,
                creditCardBrand: CreditCardBrand.VISA
        )

        when:
        def response = installmentService.calculateInstallments(request)

        then:
        response.numberOfInstallments == 1
        response.installmentValue == new BigDecimal("100.00")
        response.totalAmount == new BigDecimal("100.00")
    }

    def "should throw exception for invalid number of installments"() {
        given:
        def request = new InstallmentRequest(
                purchaseAmount: new BigDecimal("100.00"),
                numberOfInstallments: 7,
                creditCardBrand: CreditCardBrand.AMEX
        )

        when:
        installmentService.calculateInstallments(request)

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception for purchase amount below minimum"() {
        given:
        def request = new InstallmentRequest(
                purchaseAmount: new BigDecimal("9.99"),
                numberOfInstallments: 1,
                creditCardBrand: CreditCardBrand.VISA
        )

        when:
        installmentService.calculateInstallments(request)

        then:
        thrown(IllegalArgumentException)
    }

    @Unroll
    def "should calculate installments with correct interest for #numberOfInstallments installment(s)"() {
        given:
        def request = new InstallmentRequest(
                purchaseAmount: new BigDecimal("100.00"),
                numberOfInstallments: numberOfInstallments,
                creditCardBrand: CreditCardBrand.MASTERCARD
        )

        when:
        def response = installmentService.calculateInstallments(request)

        then:
        response.numberOfInstallments == numberOfInstallments
        response.installmentValue == expectedInstallmentValue.setScale(2, RoundingMode.HALF_UP)
        response.totalAmount == expectedTotalAmount.setScale(2, RoundingMode.HALF_UP)

        where:
        numberOfInstallments | expectedInstallmentValue | expectedTotalAmount
        2                    | new BigDecimal("51.50")  | new BigDecimal("102.99")
        3                    | new BigDecimal("34.67")  | new BigDecimal("104.01")
        4                    | new BigDecimal("26.26")  | new BigDecimal("105.02")
        5                    | new BigDecimal("21.21")  | new BigDecimal("106.05")
        6                    | new BigDecimal("17.85")  | new BigDecimal("107.08")
    }
}

