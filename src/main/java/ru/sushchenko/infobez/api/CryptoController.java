package ru.sushchenko.infobez.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
@Tag(name = "Криптографический контроллер", description = "Контроллер для реализации необходимых методов шифрования")
public class CryptoController {
}
