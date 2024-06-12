package elmaguiri.backend.Prestation.DTOs;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtapePrestationDTO {
    private Long id;
    private String name;
    private Map<String, Object> attributesTemplate;
}
