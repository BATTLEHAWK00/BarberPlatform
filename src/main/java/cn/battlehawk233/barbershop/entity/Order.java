package cn.battlehawk233.barbershop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "bs_order")
@Entity
@DynamicUpdate
@Data
public class Order {
    @Id
    @GeneratedValue
    int oid;
    int sponsorId;
    int ownerId;
    int state;
}
