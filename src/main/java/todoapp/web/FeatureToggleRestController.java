package todoapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import todoapp.web.model.FeatureTogglesProperties;

@RestController
public class FeatureToggleRestController {

    private FeatureTogglesProperties featureTogglesProperties;

    public FeatureToggleRestController(FeatureTogglesProperties featureTogglesProperties) {
        this.featureTogglesProperties = featureTogglesProperties;
    }

    @GetMapping("/api/feature-toggles")
    public FeatureTogglesProperties toggle() {
        return featureTogglesProperties;
    }

}
