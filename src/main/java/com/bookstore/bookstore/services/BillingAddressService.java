package com.bookstore.bookstore.services;


import com.bookstore.bookstore.models.BillingAddress;
import com.bookstore.bookstore.models.UserBilling;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
