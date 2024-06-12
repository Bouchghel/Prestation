package elmaguiri.backend.Prestation.DTOs;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OperationDto {
    private Long id;
    private String status;
    private Long clientId;
    private Long prestationId;
    private List<EtapeOperationDTO> etapes;
}
