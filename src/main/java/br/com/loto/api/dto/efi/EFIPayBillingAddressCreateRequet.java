
package br.com.loto.api.dto.efi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EFIPayBillingAddressCreateRequet {

    private String street;

    private int number;

    private String neighborhood;

    private String zipcode;

    private String city;

    private String state;
}
