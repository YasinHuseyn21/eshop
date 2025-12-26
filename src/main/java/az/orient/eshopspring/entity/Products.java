package az.orient.eshopspring.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Categories categoryId;
    private String name;
    private String description;
    private double price;
    private String status;
    @Column(name = "created_at",columnDefinition = "TIMESTA")
    private Date createdAt;
    private int active;


}
