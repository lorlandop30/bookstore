package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.UserShipping;
import com.bookstore.bookstore.models.ShippingAddress;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
