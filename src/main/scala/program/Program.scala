package fr.esgi.mower
package program

import domain.FileType
import engine.{EngineFactory, FileEngine}

object Program {
  val engineFactory: EngineFactory = new EngineFactory()

  def run(): Unit = {
    println("Enter the input path: ");
    val inputPath: String = scala.io.StdIn.readLine();
    println("Enter the output path: ");
    val outputPath: String = scala.io.StdIn.readLine();
    println("Enter the output format: ");
    val outputFormat: String = scala.io.StdIn.readLine();

    println("Mower on the way...");
    val engine: FileEngine = engineFactory.get(FileType.withName(outputFormat.toUpperCase())).asInstanceOf[FileEngine];
    engine.run((inputPath, outputPath));
    println("The output file is available at: " + outputPath);
  }
}
