package ar.cpfw.book.radio.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import ar.cpfw.book.radio.model.Competitor;
import ar.cpfw.book.radio.model.RadioCompetition;
import ar.cpfw.book.radio.model.RadioException;
import ar.cpfw.book.radio.model.RadioProgram;

public class InscriptionView {

	private RadioProgram radioProgram;

	private JPanel contentPane;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JComboBox<Item> comboBox;
	private JButton btnOk;
	private JLabel lblCompetition;

	public InscriptionView(RadioProgram radioProgram) {
		this.radioProgram = radioProgram;
		makeUi();
	}

	private void makeUi() {
		var frame = new JFrame("Inscription to Competition");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 451, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);

		formElements();
		layout();
		frame.setVisible(true);
	}

	private void formElements() {
		lblName = new JLabel("Name:");

		txtName = new JTextField();
		txtName.setColumns(10);

		lblLastName = new JLabel("Last name:");

		txtLastName = new JTextField();
		txtLastName.setColumns(10);

		lblId = new JLabel("Id:");

		txtId = new JTextField();
		txtId.setColumns(10);

		lblPhone = new JLabel("Phone:");

		txtPhone = new JTextField();
		txtPhone.setColumns(10);

		lblEmail = new JLabel("Email:");

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		btnOk = new JButton("Ok");

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var worker = new SwingWorker<Void, Void>() {
					@Override
					protected void done() {
						try {
							btnOk.setEnabled(true);
							get();
							JOptionPane.showMessageDialog(contentPane,
									"Inscription done successfully !");
						} catch (ExecutionException e) {
							if (RadioException.class
									.equals(e.getCause().getClass()))
								JOptionPane.showMessageDialog(contentPane,
										e.getMessage());
							else
								JOptionPane.showMessageDialog(contentPane,
										"Something went wrong...");
							throw new RuntimeException(e);
						} catch (InterruptedException e) {
							JOptionPane.showMessageDialog(contentPane,
									"Something went wrong...");
							throw new RuntimeException(e);
						}
					}

					@Override
					protected Void doInBackground() throws Exception {
						btnOk.setEnabled(false);
						saveInscription();
						// to make the compiler happy...
						return null;
					}
				};

				worker.execute();
			}
		});

		lblCompetition = new JLabel("Competition:");
		allCompetitions();
	}

	private void allCompetitions() {
		var competitions = new ArrayList<Item>();
		competitions.add(new Item(0, "Select One"));

		for (RadioCompetition comp : radioProgram
				.availableCompetitions()) {
			competitions.add(new Item(comp.id(), comp.description()));
		}

		this.comboBox = new JComboBox<Item>(new CompetitionComboBoxModel(
				competitions.toArray(new Item[competitions.size()])));
	}

	private void saveInscription() {
		radioProgram.addInscription(
				((Item) this.comboBox.getSelectedItem()).id(),
				new Competitor() {
					@Override
					public String phone() {
						return txtPhone.getText();
					}

					@Override
					public String name() {
						return txtName.getText();
					}

					@Override
					public String lastName() {
						return txtLastName.getText();
					}

					@Override
					public String id() {
						return txtId.getText();
					}

					@Override
					public String email() {
						return txtEmail.getText();
					}
				});
	}

	private void layout() {
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane
										.createSequentialGroup()
										.addGroup(gl_contentPane
												.createParallelGroup(
														Alignment.LEADING)
												.addComponent(lblLastName)
												.addComponent(lblId)
												.addComponent(lblPhone)
												.addComponent(lblEmail)
												.addComponent(lblName)
												.addComponent(
														lblCompetition))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												28, Short.MAX_VALUE)
										.addGroup(gl_contentPane
												.createParallelGroup(
														Alignment.LEADING,
														false)
												.addComponent(comboBox, 0,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(txtEmail,
														Alignment.TRAILING)
												.addComponent(txtPhone,
														Alignment.TRAILING)
												.addComponent(txtId,
														Alignment.TRAILING)
												.addComponent(txtLastName,
														Alignment.TRAILING)
												.addComponent(txtName,
														Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE,
														298,
														Short.MAX_VALUE)))
								.addComponent(btnOk, Alignment.TRAILING,
										GroupLayout.PREFERRED_SIZE, 86,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtName,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLastName)
								.addComponent(txtLastName,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblId).addComponent(txtId,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane
										.createSequentialGroup()
										.addComponent(lblPhone)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblEmail))
								.addGroup(gl_contentPane
										.createSequentialGroup()
										.addComponent(txtPhone,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(txtEmail,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(gl_contentPane
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(comboBox,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lblCompetition))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnOk)
						.addContainerGap(67, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
