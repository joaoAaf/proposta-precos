package br.com.apisemaperreio.relatorios_api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apisemaperreio.relatorios_api.model.dto.gemini.GeminiRequest;
import br.com.apisemaperreio.relatorios_api.model.dto.gemini.GeminiResponse;

@FeignClient(name = "geminiClient", url = "${gemini.api.url}")
public interface GeminiFeignClient {

    @PostMapping
    GeminiResponse consultarGemini(@RequestBody GeminiRequest request, @RequestParam("key") String apiKey);

}
