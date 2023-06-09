package giuseppecanzoneri.javaspringproject.dispositivo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import giuseppecanzoneri.javaspringproject.payload.DispositivoPayload;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
	@Autowired
	private DispositivoService ds;

	@GetMapping("")
	public Page<Dispositivo> getDispositivi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return ds.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoPayload body) {
		return ds.create(body);
	}

	@GetMapping("/{dispositivoId}")
	public Dispositivo getDispositivo(@PathVariable UUID dispositivoId) throws Exception {
		return ds.findById(dispositivoId);
	}

	@PutMapping("/{dispositivoId}")
	public Dispositivo updateDispositivo(@PathVariable UUID dispositivoId, @RequestBody DispositivoPayload body)
			throws Exception {
		return ds.findByIdAndUpdate(dispositivoId, body);
	}

	@DeleteMapping("/{dispositivoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDispositivo(@PathVariable UUID dispositivoId) throws Exception {
		ds.findByIdAndDelete(dispositivoId);
	}

}