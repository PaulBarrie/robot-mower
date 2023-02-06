package progfun.parser.input

import progfun.domain.Input.{Input, InputItem}
import progfun.domain.{Orientation, State}
import progfun.error.DonneesIncorrectesException
import progfun.parser.Parser

object InputParser {
  private val inputItemParser: InputItemParser = new InputItemParser;

  case class InputParser() extends Parser[List[String], Input] {
    override def marshall(
        input: List[String]
    ): Either[DonneesIncorrectesException, Input] = {
      input.headOption match {
        case Some(head) =>
          if (!isValidMapSize(head)) {
            Left(
              DonneesIncorrectesException(
                "La position de la tondeuse doit être composée de 2 entiers séparés par un espace"
              )
            )
          } else {
            val inputItems = input.drop(1).grouped(2).toList
            val inputItemsParsed = inputItems.map(inputItemParser.marshall)
            inputItemsParsed.find(_.isLeft) match {
              case Some(_) =>
                Left(
                  DonneesIncorrectesException(
                    "Les données de la tondeuse sont incorrectes"
                  )
                )
              case None =>
                input.headOption match {
                  case Some(value) =>
                    Right(
                      Input(
                        parseMapSize(value),
                        inputItemsParsed.map {
                          case Right(value) => value
                          case Left(_) =>
                            InputItem(State((0, 0), Orientation.N), List())
                        }
                      )
                    )
                  case None =>
                    Left(
                      DonneesIncorrectesException(
                        "Les données de la tondeuse sont incorrectes"
                      )
                    )
                }

            }
          }
        case None => Left(DonneesIncorrectesException("L'input est vide"))
      }
    }

    private def isValidMapSize(mapSize: String): Boolean = {
      val values = mapSize.split(" ")
      values.length == 2 && values.forall(_.forall(_.isDigit))
    }

    private def parseMapSize(input: String): (Int, Int) = {
      val sizeList = input.split(" ").map(_.toInt).toList
      sizeList match {
        case head :: _ => (head, sizeList(1))
        case Nil       => (0, 0)
      }
    }

    override def unmarshall(input: Input): String = {
      ""
    }
  }
}
