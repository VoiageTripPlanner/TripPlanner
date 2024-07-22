package com.project.demo.repository;

import com.project.demo.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    // Methods that interact directly with JPA or the Database.
    Optional<Currency> findByCurrencyName(String currencyName);
}
