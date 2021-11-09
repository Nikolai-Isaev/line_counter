import java.io.File
import scala.util.{Failure, Success, Try}

object Validation {

  def getValidInitialDirectory(rawPath: String): Try[File] = {
    val dir = new File(rawPath)
    if (dir.isDirectory) Success(dir)
    else Failure(NoSuchDirectoryException())
  }

  def getValidFileExtension(ext: String): Try[String] = {
    val matcher = "\\*\\.\\w*"
    if (!ext.matches(matcher)) Failure(InvalidExtensionException())
    else Success(ext.dropWhile(_ != '.'))
  }

  def getValidInput(inputArr: Array[String]): Try[(String, String)] =
    if (inputArr.length == 2) Success((inputArr(0), inputArr(1)))
    else Failure(CLArgumentsException())


  abstract class LineCounterException(message: String) extends RuntimeException(message)

  final case class InvalidExtensionException(message: String = "Second parameter should be file extension")
    extends LineCounterException(message)

  final case class NoSuchDirectoryException(message: String = "Directory with that name does not exist")
    extends LineCounterException(message)

  final case class CLArgumentsException(message: String = "Directory name and file extension expected")
    extends LineCounterException(message)

}
