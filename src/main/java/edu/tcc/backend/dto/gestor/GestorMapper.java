package edu.tcc.backend.dto.gestor;

import edu.tcc.backend.model.gestor.Gestor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GestorMapper {

    public Gestor toEntity(GestorRequest request) {
        return Gestor.builder()
                .nome(request.getNome())
                .senha(request.getSenha())
                .build();
    }

    public GestorResponse toResponse(Gestor gestor) {
        return GestorResponse.builder()
                .id(gestor.getId())
                .nome(gestor.getNome())
                .build();
    }

    public List<GestorResponse> toResponseList(final List<Gestor> gestors) {
        if (gestors == null || gestors.isEmpty()) {
            return List.of();
        }
        return gestors.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
