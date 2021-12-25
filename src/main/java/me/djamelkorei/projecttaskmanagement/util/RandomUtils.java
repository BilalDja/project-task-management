package me.djamelkorei.projecttaskmanagement.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

/**
 * Utility class for Random String Generation.
 *
 * @author Djamel Eddine Korei
 */
public final class RandomUtils {

  private static final int DEF_COUNT = 7;
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  static {
    SECURE_RANDOM.nextBytes(new byte[64]);
  }

  private RandomUtils() {
  }

  public static String generateRandomAlphanumericString() {
    return RandomStringUtils.random(DEF_COUNT, 0, 0, true, true, null, SECURE_RANDOM);
  }

  public static String generatePassword() {
    return generateRandomAlphanumericString();
  }

  public static String generateResetKey() {
    return generateRandomAlphanumericString();
  }

}
