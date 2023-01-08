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
      case 2 => if (isValidPosition(input.head)) {
        try {
          Right(InputItem(parsePosition(input.head), parseInstructions(input(1))));
        } catch {
          case _: Exception => Left(DonneesIncorrectesException("Les instructions doivent être composées de caractères valides"))
        }
      }
      else {
        Left(DonneesIncorrectesException("La position de la tondeuse doit être composée de 2 entiers et d'une orientation séparés par un espace"))
      }
      case _ => Left(DonneesIncorrectesException("L'input item parser doit recevoir une liste de 2 string et non :" + input.length))
    }
  }

  private def isValidPosition(position: String): Boolean = {
    val values = position.split(" ")
    values.length == 3 && values(0).forall(_.isDigit) && values(1).forall(_.isDigit) && values(2).forall(_.isLetter)
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
