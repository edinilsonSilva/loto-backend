package br.com.loto.service.efi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 05/10/2024
 */

@Getter
@AllArgsConstructor
public enum EFIPayRoute {

    AUTHORIZE("/v1/authorize", HttpMethod.POST),
    CREATE_CHARGE("/v1/charge", HttpMethod.POST),
    CREATE_ONE_STEP_CHARGE("/v1/charge/one-step", HttpMethod.POST),
    CREATE_ONE_STEP_CHARGE_PARTNER("/v1/partner/charge/one-step", HttpMethod.POST),
    DETAIL_CHARGE("/v1/charge/:id", HttpMethod.GET),
    UPDATE_CHARGE_METADATA("/v1/charge/:id/metadata", HttpMethod.PUT),
    UPDATE_BILLET("/v1/charge/:id/billet", HttpMethod.PUT),
    DEFINE_PAY_METHOD("/v1/charge/:id/pay", HttpMethod.POST),
    DEFINE_PAY_METHOD_PARTNER("/v1/partner/charge/:id/pay", HttpMethod.POST),
    CANCEL_CHARGE("/v1/charge/:id/cancel", HttpMethod.PUT),
    LIST_CHARGES("/v1/charges", HttpMethod.GET),
    CREATE_CARNET("/v1/carnet", HttpMethod.POST),
    DETAIL_CARNET("/v1/carnet/:id", HttpMethod.GET),
    UPDATE_CARNET_PARCEL("/v1/carnet/:id/parcel/:parcel", HttpMethod.PUT),
    UPDATE_CARNET_PARCELS("/v1/carnet/:id/parcels", HttpMethod.PUT),
    UPDATE_CARNET_METADATA("/v1/carnet/:id/metadata", HttpMethod.PUT),
    GET_NOTIFICATION("/v1/notification/:token", HttpMethod.GET);

    private final String route;
    private final HttpMethod method;
}
