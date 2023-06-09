package giuseppecanzoneri.javaspringproject.dispositivo;

import java.util.UUID;

import giuseppecanzoneri.javaspringproject.entities.Stato;
import giuseppecanzoneri.javaspringproject.entities.Tipo;
import giuseppecanzoneri.javaspringproject.utente.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "dispositivi")
@NoArgsConstructor
public class Dispositivo {
	@Id
	@GeneratedValue
	private UUID id;
	private String marca;
	@Enumerated(EnumType.STRING)
	private Tipo tipoDispositivo;
	@Enumerated(EnumType.STRING)
	private Stato statoDispositivo;

	@ManyToOne
	private Utente utente;

	public Dispositivo(String marca, Tipo tipoDispositivo, Stato statoDispositivo) {
		this.marca = marca;
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
		this.utente = null;
	}

}