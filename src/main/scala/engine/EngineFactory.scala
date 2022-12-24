package fr.esgi.mower
package engine


import domain.FileType
import domain.FileType.FileType
import parser.output.OutputParser.{CSVOutputParser, JSONOutputParser, YAMLOutputParser}

class EngineFactory {
  def get(outputFileType: FileType): Any = {
    outputFileType match {
      case FileType.JSON => FileEngine(JSONOutputParser())
      case FileType.CSV => FileEngine(CSVOutputParser())
      case FileType.YAML => FileEngine(YAMLOutputParser())
    }
  }

}
