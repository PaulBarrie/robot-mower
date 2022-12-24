package fr.esgi.mower
package parser.output

import domain.Output.OutputItem
import kernel.error.DonneesIncorrectesException
import parser.parser.Parser

abstract class OutputItemParser extends Parser[String, OutputItem] {}

object OutputItemParser {
  case class JsonOutputItemParser() extends Parser[String, OutputItem] {

    private final val stateParser = StateParser.JSONStateParser();

    override def marshall(input: String): Either[DonneesIncorrectesException, OutputItem] = ???

    override def unmarshall(input: OutputItem): String = {
      s"""{
         |   \"begin\": ${stateParser.unmarshall(input.begin)},
         |   \"instructions\": ${input.instructions.map(instruction => "\"" + instruction + "\"").mkString("[", ",", "]")},
         |   \"fin\": ${stateParser.unmarshall(input.end)}
         |}""".stripMargin
    }
  }

  case class CSVOutputItemParser() extends Parser[String, OutputItem] {
    final private val csvSeparator = ";";

    override def marshall(input: String): Either[DonneesIncorrectesException, OutputItem] = ???

    override def unmarshall(input: OutputItem): String = {
      s"${input.begin.position._1}${csvSeparator}${input.begin.position._2}${csvSeparator}${input.begin.orientation}${csvSeparator}${input.end.position._1}${csvSeparator}${input.end.position._2}${csvSeparator}${input.end.orientation}"
    }
  }

  case class YAMLOutputItemParser() extends Parser[String, OutputItem] {
    private final val stateParser = StateParser.YAMLStateParser();

    override def marshall(input: String): Either[DonneesIncorrectesException, OutputItem] = ???

    override def unmarshall(input: OutputItem): String = {
      s"""- begin:
         |${stateParser.unmarshall(input.begin)}
         |    instructions: \n${input.instructions.map(instruction => "      - " + instruction).mkString("\n")}
         |    end:
         |${stateParser.unmarshall(input.end)}
         |""".stripMargin
    }
  }
}

