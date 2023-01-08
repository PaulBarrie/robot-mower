package fr.esgi.mower
package engine

import domain.Input.{Input, InputItem}
import domain.Output.{Output, OutputItem}
import domain.State
import io.FileReader
import parser.input.InputParser.InputParser
import parser.parser.Parser

case class FileEngine(outputParser: Parser[String, Output]) extends Engine[(String, String), Unit] {
  private final val inputParser: InputParser = InputParser();
  private final val arrivalComputer: Computer[InputItem, State] = ArrivalComputer();

  override def run(inputOutputPath: (String, String)): Unit = {
    val fileReader1 = FileReader(inputOutputPath._1)
    val fileReader2 = FileReader(inputOutputPath._2)
    val textInput: List[String] = io.File(fileReader1.path, fileReader1.read()).toList();
    val input: Input = inputParser.marshall(textInput).right.get;
    val output: Output = Output(input.size, input.inputList.map(inputItem => OutputItem(inputItem.initState, inputItem.instructions, arrivalComputer.compute(inputItem))));
    val outputString: String = outputParser.unmarshall(output);
    io.File(fileReader2.path, "").write(outputString);
  }


  override def get(): Unit = {
  }
}
