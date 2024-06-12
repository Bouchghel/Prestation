package elmaguiri.backend.Prestation.DTOs;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtapeOperationDTO {
    private Long id;
    private String name;
    private String status;
    private Map<String, Object> attributes;
    private Long operationId;
    private Long etapePrestationId;
}
