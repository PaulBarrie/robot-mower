package progfun.program

import progfun.domain.FileType
import progfun.engine.{EngineFactory, FileEngine}

object Program {
  val engineFactory: EngineFactory = new EngineFactory()

  def run(): Unit = {
    println("Enter the input path: ");
    val inputPath: String = "C:\\Users\\ademr\\Documents\\Code\\robot-mower\\src\\test\\resources\\input.txt"
    println("Enter the output path: ");
    val outputPath: String = "C:\\Users\\ademr\\Documents\\Code\\robot-mower\\src\\test\\resources\\output.json"
    println("Enter the output format: ");
    val outputFormat: String = "json"

    println("Mower on the way...");
    val engine: FileEngine = engineFactory.get(FileType.withName(outputFormat.toUpperCase())).asInstanceOf[FileEngine];
    engine.run((inputPath, outputPath));
  }
}
