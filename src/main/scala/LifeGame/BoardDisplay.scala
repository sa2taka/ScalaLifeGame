package LifeGame

object BoardDisplay {
  def display(board: Array[Array[Boolean]],
              trueValue: Char = '▉',
              falseValue: Char = ' '): Unit = {
    for (row <- board) {
      for (column <- row) {
        print(if (column) trueValue else falseValue)
      }
      println()
    }
  }
}
