package ru.skillfactory.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactory.model.MyOrder;

@Repository
public interface OrderService extends JpaRepository<MyOrder, Long> {
    // методов не определяем - JpaRepository уже содержит набор CRUD операций
}
