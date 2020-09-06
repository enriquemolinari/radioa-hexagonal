package main;

import javax.swing.SwingUtilities;

import radio.model.DefaultRadioProgram;
import radio.persistence.JdbcCompetitionRepository;
import radio.ui.InscriptionView;

public class Main {

 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
   @Override
   public void run() {
    try {
     new Main().start();
    } catch (Exception e) {
     // log the exception...
     //Show "Ups something went wrong" to the user...
     System.out.println(e);
    }
   }
  });
 }

 private void start() {
  new InscriptionView(
    new DefaultRadioProgram(
      new JdbcCompetitionRepository(
        "app", "app", "radiocompetition", "localhost", "1527")
      )
    );
 }
}
