package fr.esgi.mower
package parser.input

import domain.Input.Input
import kernel.error.DonneesIncorrectesException
import parser.parser.Parser


object InputParser {
  private val inputItemParser: InputItemParser = new InputItemParser;

  case class InputParser() extends Parser[List[String], Input] {
    override def marshall(input: List[String]): Either[DonneesIncorrectesException, Input] = {
      if (!isValidMapSize(input.head)) {
        Left(DonneesIncorrectesException("La position de la tondeuse doit être composée de 2 entiers séparés par un espace"))
      } else {
        val inputItems = input.tail.grouped(2).toList
        val inputItemsParsed = inputItems.map(inputItemParser.marshall)
        if (inputItemsParsed.exists(_.isLeft)) {
          Left(inputItemsParsed.find(_.isLeft).get.left.get)
        } else {
          Right(Input(
            parseMapSize(input.head),
            inputItemsParsed.map(_.right.get))
          )
        }
      }
    }

    private def isValidMapSize(mapSize: String): Boolean = {
      val values = mapSize.split(" ")
      values.length == 2 && values.forall(_.forall(_.isDigit))
    }

    private def parseMapSize(input: String): (Int, Int) = {
      val sizeList = input.split(" ").map(_.toInt).toList
      (sizeList.head, sizeList(1))
    }

    override def unmarshall(input: Input): String = {
      ""
    }
  }
}

