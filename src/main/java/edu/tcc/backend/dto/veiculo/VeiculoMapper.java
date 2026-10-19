package edu.tcc.backend.dto.veiculo;

import edu.tcc.backend.model.veiculo.Veiculo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoRequest request) {
        return Veiculo.builder()
                .placa(request.getPlaca())
                .placaCarreta1(request.getPlacaCarreta1())
                .placaCarreta2(request.getPlacaCarreta2())
                .build();
    }

    public VeiculoResponse toResponse(Veiculo veiculo) {
        return VeiculoResponse.builder()
                .id(veiculo.getId())
                .placa(veiculo.getPlaca())
                .placaCarreta1(veiculo.getPlacaCarreta1())
                .placaCarreta2(veiculo.getPlacaCarreta2())
                .build();
    }

    public List<VeiculoResponse> toResponseList(final List<Veiculo> veiculos) {
        if (veiculos == null || veiculos.isEmpty()) {
            return List.of();
        }
        return veiculos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
