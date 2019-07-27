package com.bookstore.bookstore.services;

import org.springframework.stereotype.Service;

import com.bookstore.bookstore.models.BillingAddress;
import com.bookstore.bookstore.models.UserBilling;
import com.bookstore.bookstore.services.BillingAddressService;

@Service
public class BillingAddressServiceImpl implements BillingAddressService{

    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
        billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipcode(userBilling.getUserBillingZipcode());

        return billingAddress;
    }

}
