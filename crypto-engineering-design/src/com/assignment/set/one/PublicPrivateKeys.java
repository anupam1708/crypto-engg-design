package com.assignment.set.one;

import java.math.BigInteger;

public class PublicPrivateKeys {

	private BigInteger[][] secretKey;
	private BigInteger[][] publicKey;

	public PublicPrivateKeys(BigInteger[][] secretKey, BigInteger[][] publicKey) {
		this.secretKey = secretKey;
		this.publicKey = publicKey;
	}

	public BigInteger[][] getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(BigInteger[][] secretKey) {
		this.secretKey = secretKey;
	}

	public void setPublicKey(BigInteger[][] publicKey) {
		this.publicKey = publicKey;
	}

	public BigInteger[][] getPublicKey() {
		return publicKey;
	}

}
