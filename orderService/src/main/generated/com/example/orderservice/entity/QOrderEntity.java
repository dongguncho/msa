package com.example.orderservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = 1745944796L;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<OrderItemEntity, QOrderItemEntity> orderItems = this.<OrderItemEntity, QOrderItemEntity>createList("orderItems", OrderItemEntity.class, QOrderItemEntity.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public final NumberPath<Double> totalAmount = createNumber("totalAmount", Double.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QOrderEntity(String variable) {
        super(OrderEntity.class, forVariable(variable));
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderEntity(PathMetadata metadata) {
        super(OrderEntity.class, metadata);
    }

}

