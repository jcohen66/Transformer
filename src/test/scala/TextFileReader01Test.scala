import org.junit._
import org.mockito.Mockito._
import org.scalatest.junit.AssertionsForJUnit
import org.scalatest.mock._
import util.{TextFileReader, FileReader}

class TextFileReader01Test extends AssertionsForJUnit with MockitoSugar {

  @Test
  def verifyMockFileLoad() {
    val mockTextFileReader = mock[FileReader]
    val iterator = Seq("line1", "line2", "line3").toIterator
    when(mockTextFileReader.readFile("test.txt")).thenReturn(iterator)

    println(iterator.next)
    println(mockTextFileReader.readFile("test.txt").next)


    assert(iterator.next() === mockTextFileReader.readFile("test.txt"))
    verify(mockTextFileReader.readFile("test.txt"))
  }

  @Test
  def verifyActualFileLoad() {

    val filename = "/Users/jcohen66/src/scala/Transformer/test.txt"
    val tfr = TextFileReader.readFile(filename)

    assert(tfr.next() === "line1")

  }


}

