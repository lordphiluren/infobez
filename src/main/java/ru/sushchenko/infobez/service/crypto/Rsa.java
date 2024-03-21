package ru.sushchenko.infobez.service.crypto;

import java.math.BigInteger;

public class Rsa {
    private static final BigInteger p = BigInteger.valueOf(17);
    private static final BigInteger q = BigInteger.valueOf(11);
    private static final BigInteger e = BigInteger.valueOf(7);
    private static final BigInteger d = BigInteger.valueOf(23);

    private static final BigInteger n = p.multiply(q);

    public static BigInteger encode(BigInteger message) {
        return message.modPow(e, n);
    }

    public static BigInteger decode(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }
}
