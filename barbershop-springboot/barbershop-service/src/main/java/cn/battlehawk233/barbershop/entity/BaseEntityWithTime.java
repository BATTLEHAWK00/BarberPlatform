package cn.battlehawk233.barbershop.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntityWithTime {
    @LastModifiedDate
    Date updateTime;

    @CreatedDate
    Date createTime;
}
