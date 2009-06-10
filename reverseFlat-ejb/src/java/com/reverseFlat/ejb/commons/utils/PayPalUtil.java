package com.reverseFlat.ejb.commons.utils;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;

public abstract class PayPalUtil {

    private static NVPCallerServices caller;

    public static NVPCallerServices getCaller() throws PayPalException {
        if (caller == null) {
            APIProfile profile = null;

            profile = ProfileFactory.createSignatureAPIProfile();
            profile.setAPIUsername("lfgira_1232980759_biz_api1.gmail.com");
            profile.setAPIPassword("1232980766");
            profile.setSignature("A4MOxVbo.vUXFfO.IaZLMonborwxAVgdOLMFV6.spIYf2blSnUOECbxB");
            profile.setEnvironment("sandbox");

            caller = new NVPCallerServices();
            caller.setAPIProfile(profile);
        }
        return caller;
    }
}
