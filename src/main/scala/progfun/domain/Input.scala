package progfun.domain

import progfun.domain.Instruction.Instruction

object Input {
  case class Input(size: (Int, Int), inputList: List[InputItem]) {}

  case class InputItem(initState: State, instructions: List[Instruction])
}
