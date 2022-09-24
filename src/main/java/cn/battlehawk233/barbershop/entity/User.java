package cn.battlehawk233.barbershop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "bs_user")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue
    int userId;
    String username;
    String passwd;
    String salt;
    String phone;
    Integer gender;
    Date birthDate;
    String remark;
    BigDecimal balance;
    String openId;
    Date lastConsumeTime;
    Date regTime;
}
