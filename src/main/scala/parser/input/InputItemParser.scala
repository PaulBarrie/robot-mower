package fr.esgi.mower
package parser.input

import domain.Input.InputItem
import domain.Instruction.Instruction
import domain.{Instruction, Orientation, State}
import kernel.error.DonneesIncorrectesException
import parser.parser.Parser

class InputItemParser extends Parser[List[String], InputItem] {
  override def marshall(input: List[String]): Either[DonneesIncorrectesException, InputItem] = {
    input.length match {
      case 2 => Right(InputItem(parsePosition(input.head), parseInstructions(input(1))));
      case _ => Left(DonneesIncorrectesException("L'input item parser doit recevoir une liste de 2 string et non :" + input.length))
    }
  }

  private def parseInstructions(instruction: String): List[Instruction] = {
    instruction.split("").map(Instruction.withName).toList
  }

  private def parsePosition(position: String): State = {
    val positionList = position.split(" ")
    State((positionList(0).toInt, positionList(1).toInt), Orientation.withName(positionList(2)))
  }

  override def unmarshall(input: InputItem): String = {
    ""
  }
}
