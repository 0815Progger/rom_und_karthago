package test

import data.ConsoleGame

object Test {

  def main(args: Array[String]) {
  }
  ConsoleGame.intro();
  ConsoleGame.seiteWaehlen(); //Spieler1 wählt seine Seite. Spieler2 ist autom. die andere Seite.
  ConsoleGame.vorverteilung;
  ConsoleGame.spielzug("Sagunt")
  ConsoleGame.spielerwechsel
  ConsoleGame.spielzug("Lipari")
  ConsoleGame.spielerwechsel
  ConsoleGame.spielzug("Castiglione")
  ConsoleGame.spielerwechsel
  ConsoleGame.spielzug("Barletta")
  ConsoleGame.spielerwechsel
  ConsoleGame.spielzug("Palermo")
  ConsoleGame.zeigeStaedte

}