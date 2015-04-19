package com.blacklogik.solver

import scala.collection.BitSet

object SudokuChecker {
  def isCorrect(nums: Seq[Int]) = {
    nums.length == 9 && {
      val set: BitSet = BitSet(nums: _*)
      set.size == 9 && set.head == 1 && set.last == 9
    }
  }

  def hasPotential(nums: Seq[Int], x: Int) = {
    BitSet(x +: nums: _*).size == nums.length + 1
  }

  def correct(grid: Block) = {
    //rows
    lazy val rowsCorrect = grid forall {
      isCorrect(_)
    }

    // cols
    lazy val colsCorrect = grid.transpose forall {
      isCorrect(_)
    }

    // blocks
    lazy val blocksCorrect = grid.grouped(3) flatMap (_.transpose.grouped(3)) forall {
      correctBlock
    }

    rowsCorrect && colsCorrect && blocksCorrect
  }

  def correctBlock(x: Block): Boolean = {
    isCorrect(x.flatten)
  }
}
