package cn.battlehawk233.barbershop.layer.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LoginLog {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name = "adminId")
    Admin admin;

    @CreatedDate
    Date loginTime;

    @Column(length = 64, nullable = false)
    String loginIp;


    @Column(length = 512, nullable = false)
    String loginUa;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Enumerated
    LoginType loginType;
}
