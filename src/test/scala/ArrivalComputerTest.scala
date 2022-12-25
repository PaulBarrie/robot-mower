package fr.esgi.mower

import domain.Input.InputItem
import domain.{Instruction, Orientation, State}
import engine.ArrivalComputer

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class ArrivalComputerTest extends AnyFlatSpec with should.Matchers {
  "ArrivalComputer" should "compute the arrival state of a mower" in {
    val arrivalState = ArrivalComputer().compute(
      InputItem(
        State((1, 2), Orientation.N),
        List(Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.A)
      ))
    arrivalState should be(State((2, 1), Orientation.E))
  }
}
