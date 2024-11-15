package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.entity.QOrderEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class OderCustomRepositoryImpl implements OderCustomRepository {
  private final JPAQueryFactory jpaQueryFactory;
  private final QOrderEntity orderEntity = QOrderEntity.orderEntity;

  @Autowired
  public OderCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<OrderEntity> findOrderByUserIdAndStatusIsNew(Long userId) {
    return jpaQueryFactory
        .selectFrom(orderEntity)
        .where(orderEntity.userId.eq(userId)
            .and(orderEntity.status.eq("NEW")))
        .fetch();
  }
}
