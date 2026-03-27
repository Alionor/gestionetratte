package it.prova.gestionetratte.web.api.exception;

public class NotRemovableIfIsNotAnnullataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotRemovableIfIsNotAnnullataException(String message) {
		super(message);
	}
}
