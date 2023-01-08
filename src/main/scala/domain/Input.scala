package fr.esgi.mower
package domain

import domain.Instruction.Instruction

object Input {
  case class Input(size: (Int, Int), inputList: List[InputItem]) {
  }

  case class InputItem(initState: State, instructions: List[Instruction])
}

