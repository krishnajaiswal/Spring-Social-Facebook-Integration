package facebook.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaultConfiguration {

    @Value("${cid}")
    public String cid;

    @Value("${secret}")
    public String secret;

    public String getAppId() {
        return cid;
    }

    public void setAppId(String cid) {
        this.cid = cid;
    }

    public String getAppSecret() {
        return secret;
    }

    public void setAppSecret(String secret) {
        this.secret = secret;
    }
}
