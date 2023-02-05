package progfun.parser.output

import progfun.domain.State
import progfun.error.DonneesIncorrectesException
import progfun.parser.Parser

abstract class StateParser extends Parser[String, State] {}

object StateParser {
  case class JSONStateParser() extends StateParser {
    override def marshall(input: String): Either[DonneesIncorrectesException, State] = ???

    override def unmarshall(input: State): String = {
      s"""{
         |\t\t\t\t\"point\": {
         |\t\t\t\t\t\"x\": ${input.position._1.toString},
         |\t\t\t\t\t\"y\": ${input.position._2.toString}
         |\t\t\t\t},
         |\t\t\t\t\"direction\": \"${input.orientation.toString}\"
         |\t\t\t}""".stripMargin
    }
  }

  case class YAMLStateParser() extends StateParser {
    override def marshall(input: String): Either[DonneesIncorrectesException, State] = ???

    override def unmarshall(input: State): String = {
      s"""      point:
         |        x: ${input.position._1.toString}
         |        y: ${input.position._2.toString}
         |      direction: ${input.orientation.toString}
         |""".stripMargin
    }
  }
}

