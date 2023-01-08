package fr.esgi.mower
package parser.output

import domain.State
import kernel.error.DonneesIncorrectesException
import parser.parser.Parser

abstract class StateParser extends Parser[String, State] {}

object StateParser {
  case class JSONStateParser() extends StateParser {
    override def marshall(input: String): Either[DonneesIncorrectesException, State] = ???

    override def unmarshall(input: State): String = {
      s"""{
         |\t\t\t\t\"point\": {
         |\t\t\t\t\t\"x\": ${input.position._1},
         |\t\t\t\t\t\"y\": ${input.position._2}
         |\t\t\t\t},
         |\t\t\t\t\"direction\": \"${input.orientation}\"
         |\t\t\t}""".stripMargin
    }
  }

  case class YAMLStateParser() extends StateParser {
    override def marshall(input: String): Either[DonneesIncorrectesException, State] = ???

    override def unmarshall(input: State): String = {
      s"""      point:
         |        x: ${input.position._1}
         |        y: ${input.position._2}
         |      direction: ${input.orientation}
         |""".stripMargin
    }
  }
}

