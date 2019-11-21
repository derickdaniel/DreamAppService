package com.datingapp.datingapp.service;

import com.datingapp.datingapp.PhoneCountryCodeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformationService {

    public List<String> getLoginInfo() {

        final List<String> countryCodes = new ArrayList<>();
        PhoneCountryCodeEnum[] phoneCountryCodes = PhoneCountryCodeEnum.values();
        for (int i = 0; i < phoneCountryCodes.length; i++) {
            countryCodes.add(phoneCountryCodes[i].toString());
        }
        return countryCodes;
    }
}
