package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "ord_pack")
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderContainer orderContainer = (OrderContainer) o;
        return number.equals(orderContainer.number) &&
                Objects.equals(date, orderContainer.date) &&
                Objects.equals(status, orderContainer.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, date, status);
    }

    public void update(Date date, String status) throws ParseException {
        this.date = date;
        this.status = status;
    }

}
