package progfun.parser.output

import progfun.domain.Instruction.Instruction
import progfun.domain.Output.OutputItem
import progfun.error.DonneesIncorrectesException
import progfun.parser.Parser

abstract class OutputItemParser extends Parser[String, OutputItem] {}

object OutputItemParser {
  case class JsonOutputItemParser() extends Parser[String, OutputItem] {

    private final val stateParser = StateParser.JSONStateParser()

    override def marshall(
        input: String
    ): Either[DonneesIncorrectesException, OutputItem] = ???

    override def unmarshall(output: OutputItem): String = {
      s"""\t\t{
         |\t\t\t\"debut\": ${stateParser.unmarshall(output.begin)},
         |\t\t\t\"instructions\": ${output.instructions
           .map(instruction => "\"" + instruction.toString + "\"")
           .mkString("[", ",", "]")},
         |\t\t\t\"fin\": ${stateParser.unmarshall(output.end)}
         |\t\t}""".stripMargin
    }
  }

  case class CSVOutputItemParser() extends Parser[String, OutputItem] {
    final private val csvSeparator = ";"

    private def mapInstructionsToCSV(
        instructions: List[Instruction]
    ): String = {
      instructions.map(instruction => instruction.toString).mkString
    }

    override def marshall(
        input: String
    ): Either[DonneesIncorrectesException, OutputItem] = ???

    override def unmarshall(output: OutputItem): String = {
      s"${output.begin.position._1.toString}$csvSeparator${output.begin.position._2.toString}$csvSeparator${output.begin.orientation.toString}$csvSeparator${output.end.position._1.toString}$csvSeparator${output.end.position._2.toString}$csvSeparator${output.end.orientation.toString}$csvSeparator${mapInstructionsToCSV(output.instructions)}"
    }
  }

  case class YAMLOutputItemParser() extends Parser[String, OutputItem] {
    private final val stateParser = StateParser.YAMLStateParser()

    override def marshall(
        input: String
    ): Either[DonneesIncorrectesException, OutputItem] = ???

    override def unmarshall(output: OutputItem): String = {
      s"""- debut:
         |${stateParser.unmarshall(output.begin)}
         |    instructions: \n${output.instructions
           .map(instruction => "      - " + instruction.toString)
           .mkString("\n")}
         |    fin:
         |${stateParser.unmarshall(output.end)}
         |""".stripMargin
    }
  }
}
