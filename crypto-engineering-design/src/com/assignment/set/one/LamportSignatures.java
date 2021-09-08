package com.assignment.set.one;

import java.math.BigInteger;
import java.util.Random;

public class LamportSignatures {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public BigInteger[][] generateKey() {

		BigInteger[][] secretKey = new BigInteger[2][256];

		for(int i= 0; i<2; i++) {
			for(int j = 0; j<256; j++) {
				BigInteger key = new BigInteger(256, new Random());
				secretKey[i][j] = key;
			}
		}
		return secretKey;
	}

	public String sign() {

		return null;

	}

	public String verify() {

		return null;

	}

}
