package fr.esgi.mower
package parser.output

import domain.State
import kernel.error.DonneesIncorrectesException
import fr.esgi.mower.parser.parser.Parser

abstract class StateParser extends Parser[String, State] {}

object StateParser {
  case class JSONStateParser() extends StateParser {
    override def marshall(input: String): Either[DonneesIncorrectesException, State] = ???

    override def unmarshall(input: State): String = {
      s"""{
       |\"point\": {
       |    \"x\": ${input.position._1},
       |    \"y\": ${input.position._2}
       |   },
       |   \"direction\": \"${input.orientation}\"
       |}""".stripMargin
    }
  }
}

