package org.example.rfshop.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestLoggingController {

    private static final Logger logger = LoggerFactory.getLogger(TestLoggingController.class);

    @GetMapping("/info")
    public ResponseEntity<String> testInfoLog() {
        logger.info("Este es un mensaje de nivel INFO");
        return ResponseEntity.ok("Mensaje de INFO registrado");
    }

    @GetMapping("/error-test")
    public ResponseEntity<String> testErrorLog() {
        try {
            // Simulamos un error
            int result = 10 / 0;
            return ResponseEntity.ok("Resultado: " + result);
        } catch (Exception e) {
            logger.error("¡Se ha producido un error en el endpoint de prueba!", e);
            return ResponseEntity.internalServerError().body("Error registrado en los logs");
        }
    }

    @GetMapping("/debug")
    public ResponseEntity<String> testDebugLog() {
        logger.debug("Este es un mensaje de nivel DEBUG");
        return ResponseEntity.ok("Mensaje de DEBUG registrado");
    }
}
