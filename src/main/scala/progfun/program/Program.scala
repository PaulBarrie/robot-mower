package progfun.program

import progfun.domain.FileType
import progfun.engine.{EngineFactory, FileEngine}

object Program {
  private val engineFactory: EngineFactory = new EngineFactory()

  def run(): Unit = {
    println("Enter the input path: ");
    val inputPath: String = scala.io.StdIn.readLine();
    println("Enter the output path: ");
    val outputPath: String = scala.io.StdIn.readLine();
    println("Enter the output format: ");
    val outputFormat: String = scala.io.StdIn.readLine();

    println("Mower on the way...");
    val engine: FileEngine =
      engineFactory.get(FileType.withName(outputFormat.toUpperCase()))
    engine.run((inputPath, outputPath));
  }
}
