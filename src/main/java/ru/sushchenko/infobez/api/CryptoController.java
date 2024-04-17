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

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
@Tag(name = "Криптографический контроллер", description = "Контроллер для реализации необходимых методов шифрования")
public class CryptoController {
    private final CryptoService cryptoService;
    @Operation(
            summary = "Кодирование методом квадрата Полибия"
    )
    @GetMapping("/polybius")
    public String encodePolybius(@RequestParam String plainText) {
        return cryptoService.encodePolybius(plainText);
    }
    @Operation(
            summary = "Кодирование методов Плейфера"
    )
    @GetMapping("/playfair")
    public HashMap<String, Object> encodePlayfair(@RequestParam String key, @RequestParam String plainText) {
        return cryptoService.usePlayfair(key, plainText);
    }
    @Operation(
            summary = "Алгоритм шифрования и дешифрования RSA"
    )
    @GetMapping("/rsa")
    public Map<String, Map<String, BigInteger>> encodeRsa() {
        return cryptoService.useRsa();
    }
}
