package util

import java.io.{File, PrintWriter}


/**
  * Scala doesnt provide a toFile function so the
  * Java utils do the trick.
  */
object TextFileWriter extends FileWriter {
  override def writeFile(filename: String, json: String): Unit = {
    saveWithPrintWriter(filename, json)
  }

  def saveWithPrintWriter(filename: String, json: String): Unit = {
      val pw = new PrintWriter(new File(filename))
      pw.write(json)
      pw.close
  }

  /*
  def saveWithFileWriter(filename: String, json: String): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file) {
      override def writeFile(filename: String, json: String): Unit = ???
    })
    bw.write(json)
    bw.close
  }
*/

}
