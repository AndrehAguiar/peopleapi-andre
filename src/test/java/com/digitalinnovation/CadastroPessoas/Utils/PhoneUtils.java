package com.digitalinnovation.CadastroPessoas.Utils;

import com.digitalinnovation.CadastroPessoas.dto.request.PhoneDTO;
import com.digitalinnovation.CadastroPessoas.entity.Phone;
import com.digitalinnovation.CadastroPessoas.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "(31)55555-4444";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
