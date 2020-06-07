package ar.cpfw.book.radio.ui;

public class Item {

	private int id;
	private String description;

	public Item(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int id() {
		return this.id;
	}

	public String description() {
		return description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
