package tr.com.trs.customerbatch.dal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "musteri")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", allocationSize = 1)
    @GeneratedValue(generator = "customer_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    private String firstname;
    private String lastName;
    private boolean permit;

}
