package travelnote.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cookie")
public record CookieProperties(
        String tokenKey,
        boolean httpOnly,
        boolean secure,
        String domain,
        String path,
        String sameSite,
        Long maxAge
) {

}
