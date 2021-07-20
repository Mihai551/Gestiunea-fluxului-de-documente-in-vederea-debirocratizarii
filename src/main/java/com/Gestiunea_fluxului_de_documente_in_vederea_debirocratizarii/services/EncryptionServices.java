package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.nio.charset.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class EncryptionServices {

	public static String salt() {

		String salt = RandomString.getAlphaNumericString(16);

		System.out.println(salt);

		return salt;

	}

	public static String HashPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-512");

		String concatenated = passwordToHash.concat(salt);
		byte[] hashedPassword = md.digest(concatenated.getBytes(StandardCharsets.UTF_8));

		String stringPassword = new String(hashedPassword, StandardCharsets.UTF_8);
		System.out.println("String password " + stringPassword);
		return stringPassword;
	}

}
