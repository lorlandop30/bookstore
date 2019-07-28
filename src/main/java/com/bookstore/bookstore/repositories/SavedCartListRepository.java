package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.SavedCartList;
import org.springframework.data.repository.CrudRepository;

public interface SavedCartListRepository extends CrudRepository<SavedCartList, Long> {
}
