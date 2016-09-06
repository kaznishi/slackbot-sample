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

  // /yato-bot-api/v1/user/info/?email=[email]
  def userInfoByEmail(email: String) = Action.async { implicit request =>
    val err = YatoErrorResponse(false, "データ取得に失敗しました。", Seq(YatoError("存在しないユーザです")))

    val u = YatoUser("hogename", "hoge@example.com", true)
    val res = YatoUserInfoByEmailSuccessResponse(true, u)

    Future.successful(1).map(r => Ok(Extraction.decompose(err)))
//    Future.successful(1).map(r => Ok(Extraction.decompose(res)))
  }

  // /yato-bot-api/v1/user/point/?email=[email]
  def availablePointByEmail(email: String) = Action.async { implicit request =>
    val err = YatoErrorResponse(false, "データ取得に失敗しました。", Seq(YatoError("存在しないユーザです")))

    val p = YatoAvailablePoint(100)
    val res = YatoAvailablePointByEmailSuccessResponse(true, p)

//    Future.successful().map(_ => Ok(Extraction.decompose(err)))
    Future.successful().map(_ => Ok(Extraction.decompose(res)))
  }

  // /yato-bot-api/v1/admiration/post [post]fromUserEmail,toUserEmail,message,valueId,point
  def postAdmiration = Action.async(json) { implicit request =>
    val err = YatoErrorResponse(
      false,
      "登録に失敗しました。",
      Seq(
        YatoError("送り主ユーザが存在しません"),
        YatoError("送り先ユーザが存在しません"),
        YatoError("この人にはポイントを送れません"),
        YatoError("メッセージの文字数がオーバーしています"),
        YatoError("ポイントが足りません")
      )
    )

    val admirationRequest = request.body.extract[YatoAdmirationRequest]
    println(admirationRequest)

//    Future.successful().map(_ => Ok(Extraction.decompose(YatoAdmirationSuccessResponse(true))))
    Future.successful().map(_ => Ok(Extraction.decompose(err)))
  }

}

case class YatoUser(name: String, email: String, isPointAcceptable: Boolean)
case class YatoAvailablePoint(point: Int)

case class YatoAdmirationRequest(fromUserEmail: String, toUserEmail: String, message: String, valueId: Int, point: Int)

case class YatoError(message: String)
case class YatoErrorResponse(status: Boolean, message: String, errors: Seq[YatoError])
case class YatoUserInfoByEmailSuccessResponse(status: Boolean, data: YatoUser)
case class YatoAvailablePointByEmailSuccessResponse(status: Boolean, data: YatoAvailablePoint)
case class YatoAdmirationSuccessResponse(status: Boolean)



