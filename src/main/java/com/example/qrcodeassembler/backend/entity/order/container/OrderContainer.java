package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "ord_pack")
@Data
@NoArgsConstructor
public class OrderContainer {

    @Id
    @Column (unique = true)
    private String number;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String status;

    @ToString.Exclude
    @OneToMany (mappedBy = "orderContainer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VariantContainer> variantContainers;

    public OrderContainer(String number, Date date, String status) {
        this.number = number;
        this.date = date;
        this.status = status;
    }

    public OrderContainer(OrderContainer orderContainer) {
        this.number = orderContainer.number;
        this.date = orderContainer.date;
        this.status = orderContainer.status;
    }

    public void update(Date date, String status) throws ParseException {
        this.date = date;
        this.status = status;
    }

}
