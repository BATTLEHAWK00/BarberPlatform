package cn.battlehawk233.barbershop.layer.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DynamicUpdate
public class MoneyFlowLog {
    @Id
    @GeneratedValue
    Integer id;
}
