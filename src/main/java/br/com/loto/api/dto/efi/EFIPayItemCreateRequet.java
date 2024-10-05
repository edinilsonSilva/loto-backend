package br.com.loto.api.dto.efi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EFIPayItemCreateRequet {

    private String name;

    private int amount;

    private int value;
}
