package cn.battlehawk233.barbershop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Order extends BaseEntityWithTime {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "createdBy")
    Admin createdBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId")
    Customer customer;

    @OneToMany
    Set<OrderItem> items;

    @Enumerated
    OrderStatus status;

    String remark;
}
