package progfun.engine

import progfun.domain.Input.InputItem
import progfun.domain.Instruction.Instruction
import progfun.domain.{Instruction, Orientation, State}

import scala.annotation.tailrec

case class ArrivalComputer() extends Computer[InputItem, State] {
  override def compute(input: InputItem): State = {
    val state = input.initState.copy()
    helper(input.instructions, state)
  }

  @tailrec
  private def helper(instructions: List[Instruction], state: State): State = {
    instructions match {
      case Nil => state
      case head :: tail =>
        head match {
          case Instruction.A =>
            val (x, y) = state.position
            val orientation = state.orientation
            orientation match {
              case Orientation.N => helper(tail, state.setPosition((x, y + 1)))
              case Orientation.S => helper(tail, state.setPosition((x, y - 1)))
              case Orientation.E => helper(tail, state.setPosition((x + 1, y)))
              case Orientation.O => helper(tail, state.setPosition((x - 1, y)))
            }
          case Instruction.D =>
            state.orientation match {
              case Orientation.N => helper(tail, state.setOrientation(Orientation.E))
              case Orientation.S => helper(tail, state.setOrientation(Orientation.O))
              case Orientation.E => helper(tail, state.setOrientation(Orientation.S))
              case Orientation.O => helper(tail, state.setOrientation(Orientation.N))
            }
          case Instruction.G =>
            state.orientation match {
              case Orientation.N => helper(tail, state.setOrientation(Orientation.O))
              case Orientation.S => helper(tail, state.setOrientation(Orientation.E))
              case Orientation.E => helper(tail, state.setOrientation(Orientation.N))
              case Orientation.O => helper(tail, state.setOrientation(Orientation.S))
            }
        }
    }
  }
}
