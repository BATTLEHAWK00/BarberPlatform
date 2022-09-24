package cn.battlehawk233.barbershop.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class BarberItem {
    @Id
    @GeneratedValue
    Integer id;

    @Column(unique = true, nullable = false, length = 64)
    String name;

    String remark;

    @Column(nullable = false, precision = 2)
    BigDecimal price;
}
