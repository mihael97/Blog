package hr.fer.zemris.java.tecaj_13.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class provides implementation of methods that we will use to make our servlet
 * work easier
 * 
 * @author Mihael
 *
 */
public class Util {

	/**
	 * Method accepts String password and returns hashed version of it with hash
	 * SHA-1
	 * 
	 * @param parameter
	 *            - password
	 * @return hashed password
	 */
	public static String hashPassword(String parameter) {
		byte[] array = parameter.getBytes();

		MessageDigest digest;

		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException("Unknown digesting algorithm!");
		}

		digest.update(array);

		byte[] digestBytes = digest.digest();
		return bytetohex(digestBytes);
	}

	/**
	 * Method converts byte array to symbol representation
	 * 
	 * @param array
	 *            - byte array
	 * @return symbol representation
	 */
	public static String bytetohex(byte[] array) {
		StringBuilder builder = new StringBuilder();

		for (byte pomByte : array) {
			builder.append(String.format("%02x", pomByte));
		}

		return builder.toString();
	}

}
