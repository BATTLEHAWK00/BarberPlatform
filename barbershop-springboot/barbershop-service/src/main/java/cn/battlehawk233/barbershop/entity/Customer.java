package cn.battlehawk233.barbershop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Customer extends BaseEntityWithTime {
    @Id
    @GeneratedValue
    Integer id;

    @Column(length = 32, unique = true, nullable = false)
    String username;

    @Column(length = 64, nullable = false)
    String passwd;

    @Column(length = 32, nullable = false)
    String salt;

    @Column(length = 16, unique = true, nullable = false)
    String phone;

    @Column(columnDefinition = "tinyint")
    Integer gender;

    Date birthDate;

    String remark;

    @Column(precision = 2)
    BigDecimal balance;

    Date lastConsumeTime;

    @Column(precision = 2)
    BigDecimal lastConsumeAmount;
}
