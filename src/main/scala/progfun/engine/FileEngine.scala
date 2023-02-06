package progfun.engine

import progfun.domain.Input.InputItem
import progfun.domain.Output.{Output, OutputItem}
import progfun.domain.State
import progfun.io.{File, FileReader}
import progfun.parser.Parser
import progfun.parser.input.InputParser.InputParser

case class FileEngine(outputParser: Parser[String, Output])
    extends Engine[(String, String), Unit] {
  private final val inputParser: InputParser = InputParser();
  private final val arrivalComputer: Computer[InputItem, State] =
    ArrivalComputer();

  override def run(inputOutputPath: (String, String)): Unit = {
    val fileReader1 = FileReader(inputOutputPath._1)
    val fileReader2 = FileReader(inputOutputPath._2)
    val textInput: List[String] =
      File(fileReader1.path, fileReader1.read()).toList();
    inputParser.marshall(textInput) match {
      case Left(e) => println(e.message)
      case Right(input) =>
        val output: Output = Output(
          input.size,
          input.inputList.map(
            inputItem =>
              OutputItem(
                inputItem.initState,
                inputItem.instructions,
                arrivalComputer.compute(inputItem)
              )
          )
        );
        val outputString: String = outputParser.unmarshall(output);
        File(fileReader2.path, "").write(outputString);
        println("The output file is available at: " + fileReader2.path)
    }
  }

  override def get(): Unit = {}
}
