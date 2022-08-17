package facebook;

import facebook.configuration.VaultConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringFacebookExampleApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringFacebookExampleApplication.class, args);
		VaultConfiguration vaultConfiguration = context.getBean(VaultConfiguration.class);
		System.out.println("Id : " + vaultConfiguration.getAppId());
		System.out.println("Secret : " + vaultConfiguration.getAppSecret());
	}
}
