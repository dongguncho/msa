package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OderRepository extends JpaRepository<OrderEntity, Long>, OderCustomRepository {

  List<OrderEntity> findByUserId(Long orderId);
}
