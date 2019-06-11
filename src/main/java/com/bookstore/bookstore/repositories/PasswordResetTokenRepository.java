package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token );
    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

//    @Modifying
//    @Query("delete from PasswordResetToken t where t.expirydate <= ?1")
//    void deleteAllExpiredSince(Date now);

}
