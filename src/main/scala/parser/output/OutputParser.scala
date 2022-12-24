package fr.esgi.mower
package parser.output

import domain.Output.Output
import kernel.error.DonneesIncorrectesException
import parser.output.OutputItemParser.{CSVOutputItemParser, JsonOutputItemParser, YAMLOutputItemParser}
import parser.parser.Parser

abstract class OutputParser extends Parser[String, Output] {}

object OutputParser {
  case class JSONOutputParser() extends OutputParser {
    private final val outputItemParser = JsonOutputItemParser();

    override def marshall(input: String): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"""{
         |\t\"limite\": {
         |   \"x\": ${input.fieldSize._1},
         |   \"y\": ${input.fieldSize._2}
         |\t},
         |\t\"tondeuses\": [
         |   ${input.outputList.map(outputItemParser.unmarshall).mkString(",")}
         |  ]
         |}""".stripMargin
    }
  }

  case class YAMLOutputParser() extends OutputParser {
    private final val outputItemParser = YAMLOutputItemParser();

    override def marshall(input: String): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"""limite:
         |  x: ${input.fieldSize._1}
         |  y: ${input.fieldSize._2}
         |tondeuses:
         |  ${input.outputList.map(outputItemParser.unmarshall).mkString("  ")}
         |""".stripMargin
    }
  }

  case class CSVOutputParser() extends OutputParser {
    private final val outputItemParser = CSVOutputItemParser();

    override def marshall(input: String): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n" + input.outputList.map(outputItemParser.unmarshall).mkString("\n")
    }
  }
}