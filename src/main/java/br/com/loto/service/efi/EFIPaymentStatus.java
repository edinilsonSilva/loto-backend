package br.com.loto.service.efi;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 05/10/2024
 */

@Getter
@AllArgsConstructor
public enum EFIPaymentStatus {

    NEW("new"),
    WAITING("waiting"),
    IDENTIFIED("identified"),
    APPROVED("approved"),
    OPERATOR("operator"),
    PAID("paid"),
    UNPAID("unpaid"),
    REFUNDED("refunded"),
    CONTESTED("contested"),
    CANCELED("canceled"),
    SETTLED("settled"),
    LINK("link"),
    EXPIRED("expired");

    private final String description;

    public static EFIPaymentStatus fromDescription(String description) {
        for (EFIPaymentStatus status : EFIPaymentStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with description: " + description);
    }
}
