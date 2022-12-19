package fr.esgi.mower
package parser.output

import kernel.error.DonneesIncorrectesException

import fr.esgi.mower.domain.Output
import fr.esgi.mower.domain.Output.{Output, OutputItem}
import fr.esgi.mower.parser.output.OutputItemParser.{CSVOutputItemParser, JsonOutputItemParser}
import fr.esgi.mower.parser.parser.Parser

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

  case class CSVOutputParser() extends OutputParser {
    private final val outputItemParser = CSVOutputItemParser();

    override def marshall(input: String): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n" + input.outputList.map(outputItemParser.unmarshall).mkString("\n")
    }
  }
}