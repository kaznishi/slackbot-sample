package controllers

import javax.inject._

import com.github.tototoshi.play2.json4s.native.Json4s
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import org.json4s._
import org.json4s.native.JsonMethods._
import scala.concurrent.{ExecutionContext, Future, Promise}

@Singleton
class DummyYatoController @Inject()(json4s: Json4s)
  extends Controller {

  import json4s._
  implicit val formats = DefaultFormats

  // /yato-bot/user-info/by-email/[email]
  def userInfoByEmail(email: String) = Action.async { implicit request =>
    val u = YatoUser("hogename", "hoge@example.com", true)
    Future.successful(1).map(r => Ok(Extraction.decompose("data" -> u)))
  }

  // /yato-bot/point/by-email/[email]
  def availablePointByEmail(email: String) = Action.async { implicit request =>
    Future.successful().map(_ => Ok(Extraction.decompose("data" -> YatoAvailablePoint(100))))
  }

  // /yato-bot/give [post]fromUserId,toUserId,message,valueId,point
  def give = Action.async { implicit request =>
    println(request)
    println(request.body.asJson.getOrElse(""))
//    println(parse(""" { "numbers" : [1, 2, 3, 4] } """))

    Future.successful().map(_ => Ok(Extraction.decompose("data" -> YatoAvailablePoint(100))))
  }

}

case class YatoUser(name: String, email: String, isPointAcceptable: Boolean)
case class YatoAvailablePoint(point: Int)
