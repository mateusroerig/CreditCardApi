# Credit Card API

API para cálculo de parcelas de compras com cartão de crédito, criada para exemplificar a aplicação de testes utilizando
Spring Boot + Spock Framework

## Endpoint

### `POST /installments`

Calcula o valor das parcelas de uma compra, aplicando juros quando aplicável.

#### Request Body

```json
{
  "purchaseAmount": 100.00,
  "numberOfInstallments": 2,
  "creditCardBrand": "MASTERCARD"
}
```

- `purchaseAmount`: Valor total da compra (deve ser no mínimo R$ 10,00).
- `numberOfInstallments`: Número de parcelas (de 1 a 6).
- `creditCardBrand`: Bandeira do cartão de crédito.

#### Response Body

```json
{
    "numberOfInstallments": 2,
    "installmentValue": 51.50,
    "totalAmount": 102.99
}
```

- `numberOfInstallments`: Número de parcelas.
- `installmentValue`: Valor de cada parcela.
- `totalAmount`: Valor total com juros.

---

## Regras de Negócio

### Bandeiras de Cartão de Crédito Aceitas

- `VISA`
- `MASTERCARD`
- `AMEX`

### Taxas de Juros

As taxas são aplicadas para compras com mais de uma parcela:

- **1x**: 0%
- **2x**: 2,99%
- **3x**: 4,01%
- **4x**: 5,02%
- **5x**: 6,05%
- **6x**: 7,08%

O valor mínimo para parcelamento é de **R$ 10,00**.

---

## Exemplo de Requisição (cURL)

```bash
curl -X POST http://localhost:8080/installments \
-H "Content-Type: application/json" \
-d '{
  "purchaseAmount": 100.00,
  "numberOfInstallments": 2,
  "creditCardBrand": "MASTERCARD"
}'
```

---

## Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone git@github.com:mateusroerig/CreditCardApi.git
    cd CreditCardApi
    ```

2.  **Execute a aplicação usando o Gradle:**
    ```bash
    ./gradlew bootRun
    ```
    A API estará disponível em `http://localhost:8080`.

---

## Como Executar os Testes

Para rodar os testes unitários e de especificação, execute o seguinte comando:

```bash
./gradlew test
```

