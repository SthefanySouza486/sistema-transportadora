package edu.tcc.backend.dto.viagem;

import edu.tcc.backend.model.viagem.Viagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ViagemMapper {

    public Viagem toEntity(ViagemRequest request) {
        return Viagem.builder()
                .motoristaId(request.getMotoristaId())
                .veiculoId(request.getVeiculoId())
                .dataViagem(request.getDataViagem())
                .origem(request.getOrigem())
                .destino(request.getDestino())
                .dinheiroEntregue(request.getDinheiroEntregue())
                .pesoTonelada(request.getPesoTonelada())
                .valorTonelada(request.getValorTonelada())
                .build();
    }

    public ViagemResponse toResponse(Viagem viagem) {
        return ViagemResponse.builder()
                .id(viagem.getId())
                .motoristaId(viagem.getMotoristaId())
                .veiculoId(viagem.getVeiculoId())
                .dataViagem(viagem.getDataViagem())
                .origem(viagem.getOrigem())
                .destino(viagem.getDestino())
                .dinheiroEntregue(viagem.getDinheiroEntregue())
                .pesoTonelada(viagem.getPesoTonelada())
                .valorTonelada(viagem.getValorTonelada())
                .valorFrete(viagem.getValorFrete())
                .valorComissao(viagem.getValorComissao())
                .statusViagem(viagem.getStatusViagem())
                .build();
    }

    public List<ViagemResponse> toResponseList(final List<Viagem> viagens) {
        if (viagens == null || viagens.isEmpty()) {
            return List.of();
        }
        return viagens.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
