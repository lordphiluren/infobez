package ru.sushchenko.infobez.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sushchenko.infobez.service.CryptoService;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
@Tag(name = "Криптографический контроллер", description = "Контроллер для реализации необходимых методов шифрования")
public class CryptoController {
    private final CryptoService cryptoService;
    @Operation(
            summary = "Кодирование методом квадрата Полибия"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/polybius")
    public String encodePolybius(@RequestParam String plainText) {
        return cryptoService.encodePolybius(plainText);
    }
    @Operation(
            summary = "Кодирование методом квадрата Полибия"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/playfair")
    public String encodePlayfair(@RequestParam String plainText) {
        return cryptoService.encodePolybius(plainText);
    }
    @Operation(
            summary = "Кодирование методом квадрата Полибия"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/polybius")
    public String encodeRsa(@RequestParam String plainText) {
        return cryptoService.encodePolybius(plainText);
    }
    @Operation(
            summary = "Кодирование методом квадрата Полибия"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/polybius")
    public String decodeRsa(@RequestParam String plainText) {
        return cryptoService.encodePolybius(plainText);
    }
}
