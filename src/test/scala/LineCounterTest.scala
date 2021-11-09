import org.scalatest.{Matchers, WordSpec}

import java.io.File
import scala.util.Success

class LineCounterTest extends WordSpec with Matchers {

  "countLinesInDir" should {
    "count all existing lines" in {
      val dir = new File("src/test/resources/testDir/tenLinesDir")

      LineCounter.countLinesInDir(dir, ".txt") shouldBe Success(10)
    }
    "count zero if dir is empty" in {
      val dir = new File("src/test/resources/testDir/emptyDir")

      LineCounter.countLinesInDir(dir, ".txt") shouldBe Success(0)
    }
    "count zero if file is empty" in {
      val dir = new File("src/test/resources/testDir/dirWithEmptyFile")

      LineCounter.countLinesInDir(dir, ".txt") shouldBe Success(0)
    }
  }

}
