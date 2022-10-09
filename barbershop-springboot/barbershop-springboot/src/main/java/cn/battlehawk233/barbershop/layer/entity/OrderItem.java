package cn.battlehawk233.barbershop.layer.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
