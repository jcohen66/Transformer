package util

trait FileReader {
  def readFile(filename:String): Iterator[String]
}
