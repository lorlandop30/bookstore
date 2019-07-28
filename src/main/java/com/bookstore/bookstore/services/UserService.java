package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.models.UserBilling;
import com.bookstore.bookstore.models.UserPayment;
import com.bookstore.bookstore.models.UserShipping;
import com.bookstore.bookstore.security.PasswordResetToken;
import com.bookstore.bookstore.security.UserRole;

import java.util.Set;


public interface UserService {

    User findById(Long id);

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordRestTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail(String email);

    User createUser(User user, Set<UserRole> UserRoles) throws Exception;

    User save(User user);

    void createPasswordResetTokenForUser(final User user, final String token);
    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultShipping(Long userShippingId, User user);

}
