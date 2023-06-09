package giuseppecanzoneri.javaspringproject.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giuseppecanzoneri.javaspringproject.exception.NotFoundException;
import giuseppecanzoneri.javaspringproject.exception.UnauthorizedException;
import giuseppecanzoneri.javaspringproject.payload.AuthenticationSuccessfullPayload;
import giuseppecanzoneri.javaspringproject.payload.UtenteLoginPayload;
import giuseppecanzoneri.javaspringproject.payload.UtenteRegistrationPayload;
import giuseppecanzoneri.javaspringproject.utente.Utente;
import giuseppecanzoneri.javaspringproject.utente.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService UtenteService;

	@PostMapping("/register")
	public ResponseEntity<Utente> register(@RequestBody @Validated UtenteRegistrationPayload body) {
		Utente createdUser = UtenteService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UtenteLoginPayload body)
			throws NotFoundException {

		// verifico che esiste l'email nel db
		// se esiste controllo che la password e l'utente combacino con l email inserita
		// se combaciano generare un token
		// se fallisce lanciare un eccezione credenziali non valide
		Utente utente = UtenteService.findByEmail(body.getEmail());
		if (!body.getPassword().matches(utente.getPassword()))
			throw new UnauthorizedException("Credenziali non valide");
		String token = JWTTools.createToken(utente);

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}