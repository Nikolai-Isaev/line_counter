import java.io.File
import scala.io.Source
import scala.util.{Failure, Try}

object LineCounter {

  def countLinesInDir(directory: File, ext: String): Try[Int] = Try {
    def doCount(directory: File, ext: String): Int = {
      directory.listFiles().foldLeft(0)((acc, file) =>
        if (file.isDirectory) acc + doCount(file, ext)
        else if (file.getName.endsWith(ext)) acc + countLinesInFile(file)
        else acc
      )
    }

    doCount(directory, ext)
  }.recoverWith {
    case _ => Failure(ProcessingFileException())
  }

  private def countLinesInFile(file: File): Int = {
    val src = Source.fromFile(file)
    val linesNum = src.getLines().toList.size
    src.close()
    linesNum
  }

  final case class ProcessingFileException(message: String = "Unexpected error during file processing")
    extends RuntimeException(message)
}
