package radio.model;

class DefaultCompetitor implements Competitor {

	private String id;
	private String name;
	private String lastName;
	private Email email;
	private Phone phone;

	public DefaultCompetitor(String id, String name, String lastName,
			String email, String phone) {
		this.id = new NotNullNorEmpty<String>(id, "Id").value();
		this.name = new NotNullNorEmpty<String>(name, "Name").value();
		this.lastName = new NotNullNorEmpty<String>(lastName, "Last Name").value();
		this.email = new Email(email);
		this.phone = new Phone(phone);
	}
	
	public String id() {
		return id;
	}
	
	public String name() {
		return name;
	}

	public String lastName() {
		return lastName;
	}
	
	public String email() {
		return email.toString();
	}
	
	public String phone() {
		return phone.toString();
	}
}
