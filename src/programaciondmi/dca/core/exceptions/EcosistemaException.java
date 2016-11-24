package programaciondmi.dca.core.exceptions;

/**
 * Excepcion encargada de avisar al usuario cuando ocurre un error en el
 * ecosistema
 */

public class EcosistemaException extends Exception {

	private static final long serialVersionUID = -8788691695669536129L;

	public EcosistemaException() {
		super();
	}

	public EcosistemaException(String mensaje) {
		super(mensaje);
	}

}
