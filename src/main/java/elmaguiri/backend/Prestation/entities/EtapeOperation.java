package elmaguiri.backend.Prestation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "operation") // Exclure l'opération de toString pour éviter la récursion
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class EtapeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> attributes;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    @JsonBackReference
    private Operation operation;
}
