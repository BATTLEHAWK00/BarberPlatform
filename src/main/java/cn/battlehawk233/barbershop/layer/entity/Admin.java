package cn.battlehawk233.barbershop.layer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class Admin extends BaseEntityWithTime {
    @Id
    @GeneratedValue
    Integer id;

    @Column(unique = true, nullable = false, length = 32)
    String name;

    @JsonIgnore
    @Column(nullable = false, length = 64)
    String passwd;

    @Column(nullable = false, length = 16)
    String salt;

    @Column(unique = true, nullable = false, length = 32)
    String phone;

    String remark;

    @Column(columnDefinition = "tinyint")
    Integer gender;

    Date lastLogin;
}
