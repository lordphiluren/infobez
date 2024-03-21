package ru.sushchenko.infobez.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.infobez.service.crypto.PolybiusSquare;
import ru.sushchenko.infobez.service.crypto.Rsa;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CryptoService {
    public String encodePolybius(String plainText) {
        return PolybiusSquare.encodeText(plainText);
    }
    public Map<String, Map<String, BigInteger>> useRsa() {
        Map<String, Map<String, BigInteger>> map = new HashMap<>();
        Map<String, BigInteger> encoded = new HashMap<>();
        Map<String, BigInteger> decoded = new HashMap<>();
        BigInteger c1 = Rsa.encode(BigInteger.valueOf(3));
        BigInteger c2 = Rsa.encode(BigInteger.valueOf(20));
        BigInteger c3 = Rsa.encode(BigInteger.valueOf(51));
        encoded.put("m1", c1);
        encoded.put("m2", c2);
        encoded.put("m3", c3);
        map.put("Зашифрованные: ", encoded);
        BigInteger original1 = Rsa.decode(c1);
        BigInteger original2 = Rsa.decode(c2);
        BigInteger original3 = Rsa.decode(c3);
        decoded.put("m1", original1);
        decoded.put("m2", original2);
        decoded.put("m3", original3);
        map.put("Дешифрованные: ", decoded);
        return map;
    }
}
