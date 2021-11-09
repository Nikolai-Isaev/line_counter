import Validation._

import scala.util.{Failure, Success}

object CountLinesApp extends App {

  val result =
    for {
      (rawPath, ext) <- getValidInput(args)
      validExt <- getValidFileExtension(ext)
      validDir <- getValidInitialDirectory(rawPath)
      res <- LineCounter.countLinesInDir(validDir, validExt)
    } yield res

  val message = result match {
    case Failure(exception) =>
      s"""|Unfortunately, lines cannot be counted due to the following problem:
          |${exception.getMessage}""".stripMargin

    case Success(value) =>
      s"""|The total number of lines in ${args(1)} files
          |in ${args(0)} directory
          |is $value""".stripMargin
  }

  println(message)

}

