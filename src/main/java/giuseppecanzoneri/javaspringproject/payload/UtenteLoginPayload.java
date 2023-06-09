package giuseppecanzoneri.javaspringproject.payload;

import lombok.Getter;

@Getter
public class UtenteLoginPayload {
	String email;
	String password;
}