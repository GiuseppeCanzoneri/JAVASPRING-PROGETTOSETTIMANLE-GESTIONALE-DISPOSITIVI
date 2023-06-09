package giuseppecanzoneri.javaspringproject.payload;

import giuseppecanzoneri.javaspringproject.entities.Stato;
import giuseppecanzoneri.javaspringproject.entities.Tipo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DispositivoPayload {
	@NotNull(message = "La marca è obbligatoria")
	@Size(min = 3, max = 30, message = "Marca min 3 caratteri, massimo 30")
	String marca;

	@NotNull(message = "Il tipo di dispositivo è obbligatorio")
	Tipo tipoDispositivo;

	@NotNull(message = "Lo stato del dispositivo è obbligatorio")
	Stato statoDispositivo;

	public DispositivoPayload(
			@NotNull(message = "La marca è obbligatoria") @Size(min = 3, max = 30, message = "Marca min 3 caratteri, massimo 30") String marca,
			@NotNull(message = "Il tipo di dispositivo è obbligatorio") Tipo tipoDispositivo,
			@NotNull(message = "Lo stato del dispositivo è obbligatorio") Stato statoDispositivo) {
		super();
		this.marca = marca;
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
	}

}