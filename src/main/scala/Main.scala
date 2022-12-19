package fr.esgi.mower

import io.{File, io}

import fr.esgi.mower.engine.FileEngine
import fr.esgi.mower.program.Program

object Main {
  def main(args: Array[String]): Unit = {
    while(true) {
      Program.run();
    }
  }
}