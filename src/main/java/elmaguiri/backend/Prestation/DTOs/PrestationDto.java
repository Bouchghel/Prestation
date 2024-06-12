package elmaguiri.backend.Prestation.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestationDto {
    private Long id;
    private String name;
    private String description;
    private List<EtapePrestationDTO> etapes;
    private List<OperationDto> operations;
}
