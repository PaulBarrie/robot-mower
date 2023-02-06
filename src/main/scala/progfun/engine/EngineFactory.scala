package progfun.engine

import progfun.domain.FileType
import progfun.domain.FileType.FileType
import progfun.parser.output.OutputParser.{
  CSVOutputParser,
  JSONOutputParser,
  YAMLOutputParser
}

class EngineFactory {
  def get(outputFileType: FileType): FileEngine = {
    outputFileType match {
      case FileType.JSON => FileEngine(JSONOutputParser())
      case FileType.CSV  => FileEngine(CSVOutputParser())
      case FileType.YAML => FileEngine(YAMLOutputParser())
    }
  }

}
