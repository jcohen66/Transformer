package util

import scala.util.Try

/**
  * Reads a file line by line and returns
  * an iterator to be traversed by the caller.
  */
object TextFileReader extends FileReader {

  def readFile(filename: String): Iterator[String] = {

      val source = scala.io.Source.fromFile(filename)
      // val lines = try source.getLines() mkString "\n" finally source.close()
      val lines = source.getLines()

      new Iterator[String] {
        def hasNext: Boolean = {
          val hasNext = lines.hasNext
          if (!hasNext) Try {
            source.close
          }
          hasNext
        }

        def next(): String = lines.next
      }
    }
}
