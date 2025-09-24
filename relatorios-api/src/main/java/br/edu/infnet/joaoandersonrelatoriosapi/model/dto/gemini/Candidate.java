package br.edu.infnet.joaoandersonrelatoriosapi.model.dto.gemini;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Candidate(Content content, @JsonProperty("finishReason") String finishReason, Integer index,
        @JsonProperty("safetyRatings") List<SafetyRating> safetyRatings) {

}
