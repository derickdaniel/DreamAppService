package com.dream.app.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	public static String encrypt(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");	
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			System.out.println("encrypted: "+hashtext);
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String bycrypt(String rawPassword) {
		String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		return encodedPassword;
	}
	
	public static void main(String[] args) {
		System.out.println("encrypted: "+encrypt("$2a$10$GkJyckBMkRtjIMI8yih5xeE0waGZUFOFJqS4wK0TXSo.KppnGzWim"));
		System.out.println("bycrypted: "+bycrypt("12345678"));
	}

}
