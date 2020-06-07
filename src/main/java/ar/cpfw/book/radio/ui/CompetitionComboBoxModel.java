package ar.cpfw.book.radio.ui;

import javax.swing.DefaultComboBoxModel;

public class CompetitionComboBoxModel extends DefaultComboBoxModel<Item> {

	public CompetitionComboBoxModel(Item[] items) {
		super(items);
	}

	@Override
	public Item getSelectedItem() {
		return (Item) super.getSelectedItem();
	}
}