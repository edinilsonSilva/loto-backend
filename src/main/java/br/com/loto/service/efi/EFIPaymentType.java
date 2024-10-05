package br.com.loto.service.efi;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 05/10/2024
 */

@Getter
@AllArgsConstructor
public enum EFIPaymentType {

    CREDIT_CARD("credit_card"),
    DEBIT_CARD("debit_card"),
    BOLETO("boleto"),
    PIX("pix"),
    TRANSFER("transfer"),
    WALLET("wallet"),
    PAYPAL("paypal");

    private String description;

    public static EFIPaymentType fromDescription(String description) {
        for (EFIPaymentType status : EFIPaymentType.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with description: " + description);
    }
}
