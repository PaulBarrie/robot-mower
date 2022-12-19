package fr.esgi.mower
package io

import java.io.PrintWriter

case class File(final val path: String) extends io[File] {
  private var content: String = ""
  
  override def read(): File = {
    val source = scala.io.Source.fromFile(path)
    val content = source.mkString
    source.close()
    this.content = content
    this
  }

  override def write(content: String): Unit = {
    new PrintWriter(path) { write(content); close() }
  }

  override def get(): String = content
  override def toList(): List[String] = content.split("\\n").map(_.trim).toList
}
