package progfun.parser.output

import progfun.domain.Output.Output
import progfun.error.DonneesIncorrectesException
import progfun.parser.Parser
import progfun.parser.output.OutputItemParser.{
  CSVOutputItemParser,
  JsonOutputItemParser,
  YAMLOutputItemParser
}

abstract class OutputParser extends Parser[String, Output] {}

object OutputParser {
  case class JSONOutputParser() extends OutputParser {
    private final val outputItemParser = JsonOutputItemParser();

    override def marshall(
        input: String
    ): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"""{
         |\t\"limite\": {
         |\t\t\"x\": ${input.fieldSize._1.toString},
         |\t\t\"y\": ${input.fieldSize._2.toString}
         |\t},
         |\t\"tondeuses\": [
         |${input.outputList.map(outputItemParser.unmarshall).mkString(",\n")}
         |  ]
         |}""".stripMargin
    }
  }

  case class YAMLOutputParser() extends OutputParser {
    private final val outputItemParser = YAMLOutputItemParser();

    override def marshall(
        input: String
    ): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"""limite:
         |  x: ${input.fieldSize._1.toString}
         |  y: ${input.fieldSize._2.toString}
         |tondeuses:
         |  ${input.outputList.map(outputItemParser.unmarshall).mkString("  ")}
         |""".stripMargin
    }
  }

  case class CSVOutputParser() extends OutputParser {
    private final val outputItemParser = CSVOutputItemParser();

    override def marshall(
        input: String
    ): Either[DonneesIncorrectesException, Output] = ???

    override def unmarshall(input: Output): String = {
      s"numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n" + input.outputList
        .map(
          output =>
            (input.outputList
              .indexOf(output) + 1).toString + ";" + outputItemParser
              .unmarshall(output)
        )
        .mkString("\n")
    }
  }
}
