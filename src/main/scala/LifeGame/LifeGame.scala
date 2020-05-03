package LifeGame

import scala.util.Random

class LifeGame(val width: Int, val height: Int) {
  private var _board: Array[Array[Boolean]] = Array()
  private var _turn: Int = 0
  initializeBoard(0.1)

  def board = _board

  def initializeBoard(threshold: Double): Unit = {
    _turn = 0
    _board = Array.ofDim[Boolean](height, width)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        _board(y)(x) = new Random().nextDouble() < threshold
      }
    }
  }

  def initializeBoard(boardStr: String, trueStr: Char = '*'): Unit = {
    _turn = 0
    _board = Array.ofDim[Boolean](height, width)
    val orgBoard = formatStringToBoard(boardStr, trueStr)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val orgHeight = orgBoard.length
        val orgWidth = orgBoard(y % orgBoard.length).length
        board(y)(x) = orgBoard(y % orgHeight)(x % orgWidth)
      }
    }
  }

  def next(): Array[Array[Boolean]] = {
    for ((row, y) <- board.zipWithIndex) {
      for ((column, x) <- row.zipWithIndex) {
        val count = countAroundAliveCell(x, y)
        board(y)(x) = count match {
          case 2 => column
          case 3 => true
          case _ => false
        }
      }
    }
    board
  }

  private def formatStringToBoard(
    boardStr: String,
    trueStr: Char = '*'
  ): Array[Array[Boolean]] = {
    var newBoard = Array.ofDim[Boolean](height, width)

    for ((line, y) <- boardStr.split("\n").zipWithIndex) {
      for ((char, x) <- line.zipWithIndex) {
        newBoard(y)(x) = char == trueStr
      }
    }

    newBoard
  }

  private def countAroundAliveCell(x: Int, y: Int): Int = {
    val sides = List(-1, 0, 1) flatMap (
      e1 => List(-1, 0, 1) map (e2 => (e1, e2))
    )

    sides
      .filter(side => side._1 != 0 || side._2 != 0)
      .filter(
        side =>
          x + side._1 >= 0 && x + side._1 < width && y + side._2 >= 0 && y + side._2 < height
      )
      .map(side => if (board(y + side._2)(x + side._1)) 1 else 0)
      .sum[Int]
  }
}
