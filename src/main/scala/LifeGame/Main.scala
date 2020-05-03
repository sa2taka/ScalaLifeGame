import LifeGame.{BoardDisplay, LifeGame}

object Main extends App {
  val lifeGame = new LifeGame(20, 20)
  lifeGame.initializeBoard(0.1)
  BoardDisplay.display(lifeGame.board)

  while (true) {
    Thread.sleep(500)
    print("\u001b[2J") // clear screen
    BoardDisplay.display((lifeGame.next()))
  }
}
