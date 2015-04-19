package com.blacklogik.solver

import scala.collection.BitSet

object SudokuSolver {
  val NUMS = BitSet(1, 2, 3, 4, 5, 6, 7, 8, 9)

  type Solution = List[List[BitSet]]

  def solve(s: Solution) = {
    def constrain(region: List[BitSet]): List[BitSet] = {
      val constraints = region filter (_.size == 1) map (_.head)
      region map {
        (set: BitSet) =>
        set.size match {
          case 0 => throw new OverconstraintException()
          case 1 => set
          case _ => set filter (SudokuChecker.hasPotential(constraints, _))
        }
      }
    }

    val rowConstrained = s.map(constrain)

    val colConstrained = rowConstrained.transpose.map(constrain).transpose

    //TODO: figure out how to constrain blocks and then reverse the geometric transform
    val blockConstrained = rowConstrained.grouped(3).flatMap(_.transpose.grouped(3))
  }

  def solve(p: Puzzle) = {
    val s = p map {
      (l) => l.map {
        (n) => {
          n.map(BitSet(_)).getOrElse(NUMS)
        }
      }
    }

    solve(s)
  }
}

class OverconstraintException extends Exception
