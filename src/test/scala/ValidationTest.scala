import Validation.{CLArgumentsException, InvalidExtensionException, NoSuchDirectoryException}
import org.scalatest.{Matchers, WordSpec}

import java.io.File
import scala.util.{Failure, Success}

class ValidationTest extends WordSpec with Matchers {

  "getValidInput" should {
    "return tuple with two args if valid" in {
      val args = Array("/abc", "*.txt")
      val result = ("/abc", "*.txt")

      Validation.getValidInput(args) shouldBe Success(result)
    }
    "fail with CLArgumentsException with empty args" in {
      val args = Array[String]()

      Validation.getValidInput(args) shouldBe Failure(CLArgumentsException())
    }
    "fail with CLArgumentsException with args > 2" in {
      val args = Array("a", "b", "c")

      Validation.getValidInput(args) shouldBe Failure(CLArgumentsException())
    }
  }
  "getValidFileExtension" should {
    "return valid extension" in {
      val ext = "*.txt"
      val result = ".txt"

      Validation.getValidFileExtension(ext) shouldBe Success(result)
    }
    "fail with InvalidExtensionException if extension does not match" in {
      val ext = "*txt"

      Validation.getValidFileExtension(ext) shouldBe Failure(InvalidExtensionException())
    }
  }
  "getValidInitialDirectory" should {
    "return valid directory File if exists" in {
      val path = getClass.getResource("/testDir/tenLinesDir").getPath
      val file = new File(path)
      val rawPath = file.toString

      Validation.getValidInitialDirectory(rawPath) shouldBe Success(file)
    }
    "fail with NoSuchDirectoryException if directory does not exist" in {
      val failDir = "/invalidDir"

      Validation.getValidInitialDirectory(failDir) shouldBe Failure(NoSuchDirectoryException())
    }
  }

}
