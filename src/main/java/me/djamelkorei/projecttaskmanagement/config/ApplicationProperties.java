package me.djamelkorei.projecttaskmanagement.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Custom configuration properties.
 *
 * @author Djamel Eddine Korei
 */
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Getter
public class ApplicationProperties {

    private final Mail mail = new Mail();
    private final Security security = new Security();
    private final Storage storage = new Storage();

    @Getter
    public static class Security {

        private final Jwt jwt = new Jwt();

        @Getter
        @Setter
        public static class Jwt {
            private String secret;
            private int tokenValidityInSeconds;
            private int tokenValidityInSecondsForRememberMe;
        }
    }

    @Getter
    @Setter
    public static class Mail {
        private int resetKeyValiditySecond;
    }

    @Getter
    @Setter
    public static class Storage {
        private String location = "upload-dir";
    }

}
