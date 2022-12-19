package fr.esgi.mower
package domain

import domain.Instruction.Instruction

object Input {
  case class Input(size: (Int, Int), inputList: List[InputItem]) {
    def setSize(size: (Int, Int)): Input = {
      Input(size, inputList)
    }
  }
  case class InputItem(initState: State, instructions: List[Instruction])
}

