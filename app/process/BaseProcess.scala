package process

import scala.concurrent.Future

import play.api._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.libs.concurrent.Execution.Implicits._

/**
  * 常駐プロセスのベースtrait
  *
  * このアプリケーションの常駐プロセスこのトレイトを継承して実行可能にする
  */
trait BaseProcess {

  val env = new Environment(new java.io.File("."), getClass.getClassLoader, Mode.Prod)
  val context = ApplicationLoader.createContext(env)
  val loader = ApplicationLoader(context)
  val app = loader.load(context)

  def startProcess(f: => Any) = {
    Play.start(app)
    f
  }

}
