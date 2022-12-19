package fr.esgi.mower
package engine

import domain.Input.{Input, InputItem}

import fr.esgi.mower.domain.{Instruction, Orientation, State}
import fr.esgi.mower.domain.Instruction.Instruction

case class ArrivalComputer() extends Computer[InputItem, State] {
  override def compute(input: InputItem): State = {
    val state = input.initState.copy()
    for (instruction <- input.instructions) {
      instruction match {
        case Instruction.A => {
          val (x, y) = state.position
          val orientation = state.orientation
          orientation match {
            case Orientation.N => state.setPosition((x, y + 1))
            case Orientation.S => state.setPosition((x, y - 1))
            case Orientation.E => state.setPosition((x + 1, y))
            case Orientation.O => state.setPosition((x - 1, y))
          }
        }
        case Instruction.D => {
          state.orientation match {
            case Orientation.N => state.setOrientation(Orientation.E)
            case Orientation.S => state.setOrientation(Orientation.O) 
            case Orientation.E => state.setOrientation(Orientation.S)
            case Orientation.O => state.setOrientation(Orientation.N)
          }
        }
        case Instruction.G => {
          state.orientation match {
            case Orientation.N => state.setOrientation(Orientation.O)
            case Orientation.S => state.setOrientation(Orientation.E)
            case Orientation.E => state.setOrientation(Orientation.N)
            case Orientation.O => state.setOrientation(Orientation.S)
          }
        }
      }
    }
    state
  }

}
