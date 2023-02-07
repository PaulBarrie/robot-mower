import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import progfun.engine.FileEngine
import progfun.io.{File, FileReader}
import progfun.parser.output.OutputParser.{CSVOutputParser, JSONOutputParser, YAMLOutputParser}

class FileEngineTest extends AnyFlatSpec with should.Matchers {
  "FileEngine" should "read input file and produce JSON output file" in {
    FileEngine(JSONOutputParser())
      .run(("src/test/resources/input.txt", "src/test/resources/output.json"))
    val fileReader = FileReader("src/test/resources/output.json")
    val output = File(fileReader.path, fileReader.read()).toList().mkString
    val expected =
      "{\"limite\": {\"x\": 5,\"y\": 5},\"tondeuses\": [{\"debut\": {\"point\": {\"x\": 1,\"y\": 2},\"direction\": \"N\"},\"instructions\": [\"G\",\"A\",\"G\",\"A\",\"G\",\"A\",\"G\",\"A\",\"A\"],\"fin\": {\"point\": {\"x\": 1,\"y\": 3},\"direction\": \"N\"}},{\"debut\": {\"point\": {\"x\": 3,\"y\": 3},\"direction\": \"E\"},\"instructions\": [\"A\",\"A\",\"D\",\"A\",\"A\",\"D\",\"A\",\"D\",\"D\",\"A\"],\"fin\": {\"point\": {\"x\": 5,\"y\": 1},\"direction\": \"E\"}}]}"
    output should be(expected)
  }

  "FileEngine" should "read input file and produce YAML output file" in {
    FileEngine(YAMLOutputParser())
      .run(("src/test/resources/input.txt", "src/test/resources/output.yaml"))
    val fileReader = FileReader("src/test/resources/output.yaml")
    val output = File(fileReader.path, fileReader.read()).toList().mkString
    val expected =
      "limite:x: 5y: 5tondeuses:- debut:point:x: 1y: 2direction: Ninstructions:- G- A- G- A- G- A- G- A- Afin:point:x: 1y: 3direction: N- debut:point:x: 3y: 3direction: Einstructions:- A- A- D- A- A- D- A- D- D- Afin:point:x: 5y: 1direction: E"
    output should be(expected)
  }

  "FileEngine" should "read input file and produce CSV output file" in {
    FileEngine(CSVOutputParser())
      .run(("src/test/resources/input.txt", "src/test/resources/output.csv"))
    val fileReader = FileReader("src/test/resources/output.csv")
    val output = File(fileReader.path, fileReader.read()).toList().mkString
    val expected =
      "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions" +
        "1;1;2;N;1;3;N;GAGAGAGAA" +
        "2;3;3;E;5;1;E;AADAADADDA"
    output should be(expected)
  }
}
