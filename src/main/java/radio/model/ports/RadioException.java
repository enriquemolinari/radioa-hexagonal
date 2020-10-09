package radio.model.ports;

public class RadioException extends RuntimeException {

 public RadioException(Exception e) {
  super(e);
 }

	public RadioException(String msg) {
		super(msg);
	}

}
