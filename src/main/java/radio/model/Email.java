package radio.model;

class Email {

	private String email;
	private static String REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public Email(String email) {
		if (!email.matches(REGEX)) {
			throw new RadioException("Must be a valid email address...");
		}
		
		this.email = email;
	}

	@Override
	public String toString() {
		return email;
	}

}
