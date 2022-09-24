package cn.battlehawk233.barbershop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem extends BaseEntityWithTime {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    BarberItem item;

    Integer amount;

    String remark;
}
