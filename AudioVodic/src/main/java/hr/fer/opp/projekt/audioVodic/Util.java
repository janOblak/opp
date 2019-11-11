package hr.fer.opp.projekt.audioVodic;

import java.security.MessageDigest;
import java.util.Objects;

public class Util {
	
	public static String sha(String givenDigest) {
		MessageDigest messageDigest;
		String digest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");

			byte[] buff = givenDigest.getBytes();
			messageDigest.update(buff, 0, buff.length);

			byte[] dig = messageDigest.digest();
			digest = bytetohex(dig);

		} catch (Exception e) {
			System.out.println("Error.");
			System.exit(1);
		}
		return digest;
	}
	
	public static String bytetohex(byte[] array) {
		Objects.requireNonNull(array);
		StringBuilder sb = new StringBuilder();
		for (byte b : array) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public static boolean checkIsAlphabetic(String name) {
		for (int i = 0; i < name.length(); ++i) {
			if (!Character.isAlphabetic(name.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkUsername(String username) {
		for (int i = 0; i < username.length(); ++i) {
			if (!Character.isAlphabetic(username.charAt(i)) && !Character.isDigit(username.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkMail(String email) {
		int atCounter = 0;
		for (int i = 0; i < email.length(); ++i) {
			if (email.charAt(i)=='@') {
				atCounter++;
			}
			if (!Character.isAlphabetic(email.charAt(i)) && !Character.isDigit(email.charAt(i)) && email.charAt(i)!='.' && email.charAt(i)!='@') {
				return false;
			}
		}
		if (atCounter != 1) {
			return false;
		}
		return true;
	}
	
}
