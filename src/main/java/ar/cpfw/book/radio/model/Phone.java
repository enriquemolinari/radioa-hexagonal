package ar.cpfw.book.radio.model;

class Phone {

	public String number;
	private static String REGEX = "\\d{4}-\\d{6}";
	
	public Phone(String number) {
		if (!number.matches(REGEX))
			throw new RadioException("Must be a valid phone number NNNN-NNNNNN");
		
		this.number = number;
	}

	@Override
	public String toString() {
		return number;
	}
}
