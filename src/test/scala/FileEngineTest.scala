package fr.esgi.mower

import engine.FileEngine
import io.File
import parser.output.OutputParser.{JSONOutputParser, YAMLOutputParser}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class FileEngineTest extends AnyFlatSpec with should.Matchers {
  "FileEngine" should "read input file and produce JSON output file" in {
    FileEngine(JSONOutputParser()).run(("src/test/resources/input.txt", "src/test/resources/output.json"))
    val output = File("src/test/resources/output.json").read().toList().mkString
    val expected = "{\"limite\": {\"x\": 5,\"y\": 5},\"tondeuses\": [{\"begin\": {\"point\": {\"x\": 1,\"y\": 2},\"direction\": \"N\"},\"instructions\": [\"G\",\"A\",\"G\",\"A\",\"G\",\"A\",\"G\",\"A\",\"A\"],\"fin\": {\"point\": {\"x\": 1,\"y\": 3},\"direction\": \"N\"}},{\"begin\": {\"point\": {\"x\": 3,\"y\": 3},\"direction\": \"E\"},\"instructions\": [\"A\",\"A\",\"D\",\"A\",\"A\",\"D\",\"A\",\"D\",\"D\",\"A\"],\"fin\": {\"point\": {\"x\": 5,\"y\": 1},\"direction\": \"E\"}}]}"
    output should be(expected)
  }

  "FileEngine" should "read input file and produce YAML output file" in {
    FileEngine(YAMLOutputParser()).run(("src/test/resources/input.txt", "src/test/resources/output.yaml"))
    val output = File("src/test/resources/output.yaml").read().toList().mkString
    val expected = "limite:x: 5y: 5tondeuses:- begin:point:x: 1y: 2direction: Ninstructions:- G- A- G- A- G- A- G- A- Aend:point:x: 1y: 3direction: N- begin:point:x: 3y: 3direction: Einstructions:- A- A- D- A- A- D- A- D- D- Aend:point:x: 5y: 1direction: E"
    output should be(expected)
  }

}
