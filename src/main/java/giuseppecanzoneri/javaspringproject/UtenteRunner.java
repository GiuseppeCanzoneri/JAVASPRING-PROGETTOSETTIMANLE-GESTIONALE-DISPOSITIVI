package giuseppecanzoneri.javaspringproject;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import giuseppecanzoneri.javaspringproject.payload.UtenteRegistrationPayload;
import giuseppecanzoneri.javaspringproject.utente.UtenteService;

@Component
public class UtenteRunner implements CommandLineRunner {
	@Autowired
	UtenteService usersService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 3; i++) {
			try {
				String username = faker.name().username();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password = faker.internet().password();
				UtenteRegistrationPayload user = new UtenteRegistrationPayload(username, name, surname, email,
						password);
				usersService.create(user);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
