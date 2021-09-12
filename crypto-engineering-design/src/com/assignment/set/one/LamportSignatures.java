package com.assignment.set.one;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Random;

public class LamportSignatures {



	public static void main(String[] args) throws NoSuchAlgorithmException {

		LamportSignatures lamport = new LamportSignatures();
		//generate keys
		PublicPrivateKeys publicPrivateKeys = lamport.generateKey();

		String message = "Anupam";

		//sign
		Signature signature = lamport.sign(publicPrivateKeys.getSecretKey(), message);

		//verify
		boolean isValidSignature = lamport.verify(publicPrivateKeys.getPublicKey(), message, signature.getSignature());

		System.out.println("The signature is valid: " + isValidSignature);



	}

	public PublicPrivateKeys generateKey() throws NoSuchAlgorithmException {

		BigInteger[][] secretKey = new BigInteger[2][256];
		BigInteger[][] publicKey = new BigInteger[2][256];


		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		for(int i= 0; i<2; i++) {
			for(int j = 0; j<256; j++) {
				BigInteger key = new BigInteger(256, new Random());
				secretKey[i][j] = key;
				byte[] encodedhash = digest.digest(key.toByteArray());
				BigInteger hash = new BigInteger(encodedhash);
				publicKey[i][j] = hash;
			}
		}
		return new PublicPrivateKeys(secretKey, publicKey);
	}

	public Signature sign(BigInteger[][] secretKey, String message) throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
		BigInteger[] signature = new BigInteger[256];
		Signature signatureObj = new Signature(signature, true);

		BitSet bitSet = BitSet.valueOf(encodedhash);
		for (int i=0; i< 256; i++) {
			if(bitSet.get(i)) {
				if(secretKey[1][i] == null) {
					signatureObj.setSuccessful(false);
					break;
				}
				signature[i] = secretKey[1][i];
			} else {
				if(secretKey[0][i] == null) {
					signatureObj.setSuccessful(false);
					break;
				}
				signature[i] = secretKey[0][i];
			}
		}
		return signatureObj;
	}

	public boolean verify(BigInteger[][] publicKey, String message, BigInteger[] signature) throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(message.getBytes(StandardCharsets.UTF_8));

		BitSet bitSet = BitSet.valueOf(encodedhash);

		BigInteger[] messageBitBasedPublicKey = new BigInteger[256];

		for (int i=0; i< 256; i++) {
			if(bitSet.get(i)) {
				messageBitBasedPublicKey[i] = publicKey[1][i];
			} else {
				messageBitBasedPublicKey[i] = publicKey[0][i];
			}
		}

		BigInteger[] hashedSignature = new BigInteger[256];
		for (int i=0; i< 256; i++) {
			byte[] sigHash = digest.digest(signature[i].toByteArray());
			BigInteger hash = new BigInteger(sigHash);
			hashedSignature[i] = hash;
		}

		for (int i=0; i< 256; i++) {
			if(!messageBitBasedPublicKey[i].equals(hashedSignature[i])) {
				return false;
			}
		}
		return true;

	}

}
