package giuseppecanzoneri.javaspringproject;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import giuseppecanzoneri.javaspringproject.dispositivo.Dispositivo;
import giuseppecanzoneri.javaspringproject.dispositivo.DispositivoRepository;
import giuseppecanzoneri.javaspringproject.dispositivo.DispositivoService;
import giuseppecanzoneri.javaspringproject.entities.Stato;
import giuseppecanzoneri.javaspringproject.entities.Tipo;
import giuseppecanzoneri.javaspringproject.payload.DispositivoPayload;
import giuseppecanzoneri.javaspringproject.utente.UtenteRepository;
import giuseppecanzoneri.javaspringproject.utente.UtenteService;

@Component
public class DispositivoRunner implements CommandLineRunner {
	@Autowired
	DispositivoService ds;

	@Autowired
	DispositivoRepository dr;

	@Autowired
	UtenteRepository ur;

	@Autowired
	UtenteService us;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 3; i++) {
			try {
				String marca = faker.company().name();
				Tipo tipoDispositivo = faker.options().option(Tipo.class);
				Stato statoDispositivo = faker.options().option(Stato.class);

				DispositivoPayload dispositivo = new DispositivoPayload(marca, tipoDispositivo, statoDispositivo);

				ds.create(dispositivo);

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		// logica per la corretta assegnazione di uno stato ad un dispositivo assegnato
		// ad un utente

		List<Dispositivo> dispositivi = dr.findAll();
		Random random = new Random();
		for (Dispositivo dispositivo : dispositivi) {
			if (dispositivo.getUtente() != null) {
				// Il dispositivo è assegnato a un utente
				// Imposta lo stato su IN_MANUTENZIONE o ASSEGNATO
				Stato statoDispositivo = random.nextBoolean() ? Stato.IN_MANUTENZIONE : Stato.ASSEGNATO;
				dispositivo.setStatoDispositivo(statoDispositivo);
			} else {
				// Il dispositivo non è assegnato a nessun utente
				// Imposta lo stato su DISMESSO o DISPONIBILE
				Stato statoDispositivo = random.nextBoolean() ? Stato.DISMESSO : Stato.DISPONIBILE;
				dispositivo.setStatoDispositivo(statoDispositivo);
			}
			dr.save(dispositivo);
		}

	}
}
