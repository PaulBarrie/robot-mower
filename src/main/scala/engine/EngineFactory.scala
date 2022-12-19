package fr.esgi.mower
package engine


import domain.FileType
import domain.FileType.FileType
import parser.output.OutputParser.{CSVOutputParser, JSONOutputParser}

class EngineFactory {
  def get(outputFileType: FileType): Any = {
    outputFileType match {
      case FileType.JSON => FileEngine(JSONOutputParser())
      case FileType.CSV => FileEngine(CSVOutputParser())
    }
  }

}
