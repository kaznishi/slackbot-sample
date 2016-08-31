package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {


//    import datalayer.cache.MemcachedApi
//    val hoge = MemcachedApi
//    hoge.set[String]("hogehoge", "fugafuga")
//    hoge.get[String]("hogehoge").map(println(_))


//    import datalayer.dao._
//    val samples = SamplesDAO
//    val hogehoge = samples.findAll
//    hogehoge.map(r => {
//      println("fuga")
//      println(r)
//    })
//    println("hogehogehogheo")

//    import shade.memcached._
//    import scala.concurrent.ExecutionContext.Implicits.global
//    import scala.concurrent.Future
//    import scala.concurrent.duration._
//
//    val memcached = Memcached(Configuration("127.0.0.1:11211"))
//    val op: Future[Unit] = memcached.set("username", "Alex", 1.minute)
//    val result: Future[Option[String]] = memcached.get[String]("username")
//
//    result.map(println(_))

    Ok(views.html.index("Your new application is ready."))
  }

}
