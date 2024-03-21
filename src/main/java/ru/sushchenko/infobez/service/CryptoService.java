package ru.sushchenko.infobez.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.infobez.service.crypto.PolybiusSquare;

@Service
@RequiredArgsConstructor
public class CryptoService {
    public String encodePolybius(String plainText) {
        return PolybiusSquare.encodeText(plainText);
    }
}
