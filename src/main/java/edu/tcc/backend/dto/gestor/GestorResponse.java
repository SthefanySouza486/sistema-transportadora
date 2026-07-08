package edu.tcc.backend.dto.gestor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GestorResponse {

    private Long id;
    private String nome;
}
