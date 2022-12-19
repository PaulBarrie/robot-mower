package fr.esgi.mower
package parser.input

import domain.Input.{Input, InputItem}
import domain.{Orientation, State}
import kernel.error.DonneesIncorrectesException

import fr.esgi.mower.parser.parser.Parser


object InputParser {
  private val inputItemParser: InputItemParser = new InputItemParser;
  
  case class InputParser() extends Parser[List[String], Input] {
    override def marshall(input: List[String]): Either[DonneesIncorrectesException, Input] = {
      var inputRes: Input = Input((0, 0), List[InputItem]()).setSize(parseMapSize(input.head));
      val inputTailed = input.take(0) ++ input.drop((1));
      for (i <- 0 until inputTailed.length - 1 by 2) {
        try {
          val item = inputItemParser.marshall(List(inputTailed(i), inputTailed(i + 1))).right.get;
          inputRes = inputRes.copy(inputList = inputRes.inputList :+ item);
        } catch {
            case e: Exception =>  Left(DonneesIncorrectesException("Erreur lors de la lecture des données : " + e.getMessage))
        }
      }
      Right(inputRes)
    }
  
    private def parseMapSize(input: String): (Int, Int) = {
      val sizeList = input.split(" ").map(_.toInt).toList
      (sizeList.head, sizeList(1))
    }
  
    
    
    private def parseInitialState(str: String): State = {
      val splitInput = str.split(" ")
      State((splitInput(0).toInt, splitInput(1).toInt), Orientation.withName(splitInput(2)))
    }

    override def unmarshall(input: Input): String = {
      ""
    }
  }
}

