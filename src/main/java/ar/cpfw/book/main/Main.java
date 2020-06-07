package ar.cpfw.book.main;

import javax.swing.SwingUtilities;

import ar.cpfw.book.radio.model.DefaultRadioProgram;
import ar.cpfw.book.radio.persistence.JdbcCompetitionRepository;
import ar.cpfw.book.radio.ui.InscriptionView;

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
