package com.assignment.set.one;

import java.math.BigInteger;

public class Signature {
	
	private BigInteger[] signature;
	private boolean isSuccessful;
	
	public Signature(BigInteger[] signature, boolean isSuccessful) {
		this.signature = signature;
		this.isSuccessful = isSuccessful;
	}

	public BigInteger[] getSignature() {
		return signature;
	}

	public void setSignature(BigInteger[] signature) {
		this.signature = signature;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	
}
