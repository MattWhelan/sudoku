package com.blacklogik.solver

import org.junit.Assert._
import org.junit.Test

class SudokuCheckerTest {

  val COMPLETE_ROW: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

  val CORRECT_BOARD: List[List[Int]] = List(
    List(1, 2, 3,  4, 5, 6,  7, 8, 9),
    List(4, 5, 6,  7, 8, 9,  1, 2, 3),
    List(7, 8, 9,  1, 2, 3,  4, 5, 6),

    List(2, 3, 4,  5, 6, 7,  8, 9, 1),
    List(5, 6, 7,  8, 9, 1,  2, 3, 4),
    List(8, 9, 1,  2, 3, 4,  5, 6, 7),

    List(3, 4, 5,  6, 7, 8,  9, 1, 2),
    List(6, 7, 8,  9, 1, 2,  3, 4, 5),
    List(9, 1, 2,  3, 4, 5,  6, 7, 8)
  )

  val FLAWED_BOARD_ROW: List[List[Int]] = List(
    List(1, 2, 3,  4, 5, 6,  7, 8, 3),
    List(4, 5, 6,  7, 8, 9,  1, 2, 9),
    List(7, 8, 9,  1, 2, 3,  4, 5, 6),

    List(2, 3, 4,  5, 6, 7,  8, 9, 1),
    List(5, 6, 7,  8, 9, 1,  2, 3, 4),
    List(8, 9, 1,  2, 3, 4,  5, 6, 7),

    List(3, 4, 5,  6, 7, 8,  9, 1, 2),
    List(6, 7, 8,  9, 1, 2,  3, 4, 5),
    List(9, 1, 2,  3, 4, 5,  6, 7, 8)
  )

  val FLAWED_BOARD_COL: List[List[Int]] = List(
    List(1, 2, 3,  4, 5, 6,  7, 8, 9),
    List(4, 5, 6,  7, 8, 9,  1, 2, 3),
    List(7, 8, 9,  1, 2, 3,  4, 5, 6),

    List(2, 3, 4,  5, 6, 7,  8, 9, 1),
    List(5, 6, 7,  8, 9, 1,  2, 4, 3),
    List(8, 9, 1,  2, 3, 4,  5, 6, 7),

    List(3, 4, 5,  6, 7, 8,  9, 1, 2),
    List(6, 7, 8,  9, 1, 2,  3, 4, 5),
    List(9, 1, 2,  3, 4, 5,  6, 7, 8)
  )

  @Test
  def testIsCorrect(): Unit = {
    assertTrue(SudokuChecker.isCorrect(COMPLETE_ROW))
    assertFalse(SudokuChecker.isCorrect(List(1)))
    assertFalse(SudokuChecker.isCorrect(3 +: COMPLETE_ROW))
    assertFalse(SudokuChecker.isCorrect(COMPLETE_ROW.drop(1)))
    assertFalse(SudokuChecker.isCorrect(17 +: COMPLETE_ROW.drop(1)))
  }

  @Test
  def testCorrectBlock() = {
    assertTrue(SudokuChecker.correctBlock(List(
      List(1,2,3),
      List(4,5,6),
      List(7,8,9)
    )))
    assertFalse(SudokuChecker.correctBlock(List(
      List(1,2,3),
      List(4,5,6),
      List(7,8,8)
    )))
  }
  
  @Test
  def testCorrect() = {
    assertTrue(SudokuChecker.correct(CORRECT_BOARD))
    assertFalse(SudokuChecker.correct(FLAWED_BOARD_COL))
    assertFalse(SudokuChecker.correct(FLAWED_BOARD_ROW))
  }
}
