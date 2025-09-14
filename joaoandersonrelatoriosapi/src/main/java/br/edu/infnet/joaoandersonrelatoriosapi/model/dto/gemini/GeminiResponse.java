package br.edu.infnet.joaoandersonrelatoriosapi.model.dto.gemini;

import java.util.List;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.exceptions.GeminiErrorResponseException;

public record GeminiResponse(List<Candidate> candidates) {

    public String extractText() {
        if (candidates != null && !candidates.isEmpty()) {
            var firstCandidate = candidates.get(0);
            if (firstCandidate.content() != null && !firstCandidate.content().parts().isEmpty())
                return firstCandidate.content().parts().get(0).text();
        }
        throw new GeminiErrorResponseException("Erro ao gerar resposta na API do Gemini");
    }

}
