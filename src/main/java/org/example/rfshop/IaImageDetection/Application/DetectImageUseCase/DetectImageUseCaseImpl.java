package org.example.rfshop.IaImageDetection.Application.DetectImageUseCase;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class DetectImageUseCaseImpl implements DetectImageUseCase {

    private final String url = "https://api.sightengine.com";
    private WebClient webClient;
    private final double percentageLimit = 0.5;

    @Value("${API_USER_IA_IMAGE}")
    private String API_USER_IA_IMAGE;

    @Value("${API_SECRET_IA_IMAGE}")
    private String API_SECRET_IA_IMAGE;

    @PostConstruct
    public void onInit() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    @Override
    public Boolean execute(String secureUrl) {
        Map result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/1.0/check.json")
                        .queryParam("url", secureUrl)
                        .queryParam("models", "nudity-2.1,weapon,recreational_drug,medical,violence,self-harm,gambling")
                        .queryParam("api_user", this.API_USER_IA_IMAGE)
                        .queryParam("api_secret", this.API_SECRET_IA_IMAGE)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        System.out.println(result);

        return checkProbabilities(result);
    }

    private Boolean checkProbabilities(Map<String, Object> result) {
        // Verify nudity
        Map<String, Object> nudity = (Map<String, Object>) result.get("nudity");
        if (nudity != null) {
            if (checkNudityProbabilities(nudity)) return false;
        }

        // Verify weapon
        Map<String, Object> weapon = (Map<String, Object>) result.get("weapon");
        if (weapon != null) {
            if (checkWeaponProbabilities(weapon)) return false;
        }

        // Verify recreational_drug
        Map<String, Object> recreationalDrug = (Map<String, Object>) result.get("recreational_drug");
        if (recreationalDrug != null) {
            if (checkProbability(recreationalDrug, "prob")) return false;
        }

        // Verify medical
        Map<String, Object> medical = (Map<String, Object>) result.get("medical");
        if (medical != null) {
            if (checkProbability(medical, "prob")) return false;
        }

        // Verify violence
        Map<String, Object> violence = (Map<String, Object>) result.get("violence");
        if (violence != null) {
            if (checkProbability(violence, "prob")) return false;
        }

        // Verify self-harm
        Map<String, Object> selfHarm = (Map<String, Object>) result.get("self-harm");
        if (selfHarm != null) {
            if (checkProbability(selfHarm, "prob")) return false;
        }

         Map<String, Object> gambling = (Map<String, Object>) result.get("gambling");
         if (gambling != null) {
             if (checkProbability(gambling, "prob")) return false;
         }

        return true;
    }

    private Boolean checkNudityProbabilities(Map<String, Object> nudity) {
        return checkProbability(nudity, "sexual_activity") ||
                checkProbability(nudity, "sexual_display") ||
                checkProbability(nudity, "erotica") ||
                checkProbability(nudity, "very_suggestive") ||
                checkProbability(nudity, "suggestive") ||
                checkProbability(nudity, "mildly_suggestive");
    }

    private Boolean checkWeaponProbabilities(Map<String, Object> weapon) {
        Map<String, Object> classes = (Map<String, Object>) weapon.get("classes");
        return checkProbability(classes, "firearm") ||
                checkProbability(classes, "knife");
    }

    private Boolean checkProbability(Map<String, Object> category, String key) {
        if (category.get(key) != null) {
            return ((Double) category.get(key)).doubleValue() > this.percentageLimit;
        }
        return false;
    }

}
