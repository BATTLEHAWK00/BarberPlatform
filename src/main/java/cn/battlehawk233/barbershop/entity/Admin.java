package cn.battlehawk233.barbershop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bs_admin")
@DynamicUpdate
public class Admin {
    @Id
    @GeneratedValue
    Integer adminId;

    @Column(unique = true, nullable = false, length = 32)
    String name;

    @Column(nullable = false, length = 64)
    String passwd;

    @Column(nullable = false, length = 16)
    String salt;

    @Column(unique = true, nullable = false, length = 32)
    String phone;

    String remark;

    @Column(columnDefinition = "tinyint")
    Integer gender;

    Date regTime;

    Date lastLogin;
}
