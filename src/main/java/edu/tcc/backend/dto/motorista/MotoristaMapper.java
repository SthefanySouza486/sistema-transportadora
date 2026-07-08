package edu.tcc.backend.dto.motorista;

import edu.tcc.backend.model.motorista.Motorista;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MotoristaMapper {

    public Motorista toEntity(MotoristaRequest request) {
        return Motorista.builder()
                .nome(request.getNome())
                .telefone(request.getTelefone())
                .senha(request.getSenha())
                .comissao(request.getComissao())
                .build();
    }

    public MotoristaResponse toResponse(Motorista motorista) {
        return MotoristaResponse.builder()
                .id(motorista.getId())
                .nome(motorista.getNome())
                .telefone(motorista.getTelefone())
                .comissao(motorista.getComissao())
                .build();
    }

    public List<MotoristaResponse> toResponseList(final List<Motorista> motoristas) {
        if (motoristas == null || motoristas.isEmpty()) {
            return List.of();
        }
        return motoristas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
