package it.prova.gestionetratte.web.api.exception;

public class NotRemovableIfContainsTratteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotRemovableIfContainsTratteException(String message) {
		super(message);
	}
}
