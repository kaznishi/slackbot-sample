package process

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import slack.rtm._
import services._
import entities.{BotAction, BotResponse, Message}

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import services.slack.SlackResponseService
import services.yatobot.{ActionDecideService, ActionRunService}
import slack.api.{BlockingSlackApiClient, SlackApiClient}
import slack.models.Attachment

import scala.concurrent.ExecutionContext.Implicits.global._

class YatoBotActionDecider extends ActionDecider {
  def decideFromMessage(message:Message) = {
    println("action decide")

    val service = new ActionDecideService
    service.run(message)
  }
}
class YatoBotActionRunner extends ActionRunner {
  def run(optAction: Option[BotAction]) = {
    println("action run")
    val service = new ActionRunService
    service.run(optAction)
  }
}
class SlackResponser extends Responser {
  val client: BlockingSlackApiClient

  def response(optRes:Option[BotResponse]) = {
    println("response")

    val atc1 = Attachment(color = Some("danger"), text = Some("hogehoge"))
    val atc2 = Attachment(color = Some("#7CD197"), text = Some("fugafuga"))

    nonBlockingApi.postChatMessage(
      channelId = message.channel,
      text = "this is test by kaznishi.",
      asUser = Some(true),
      attachments = Some(Seq(atc1, atc2))
    )
    val service = new SlackResponseService
    service.run(optRes)

    Future.successful(Unit)
  }
}

object SlackWatchProcess extends SlackWatchProcessLike with App {
//  val chatMonitor = new SlackChatMonitor // 本当ならslackRtmClientの部分もChatMonitorで抽象化して場面に応じて中身を注入できるようにしたかったが、分からなかったので一旦放置
  val actionDecider = new YatoBotActionDecider
  val actionRunner = new YatoBotActionRunner

  val conf = ConfigFactory.load()
  val token = conf.getString("slack.token")

  implicit val system = ActorSystem("slack")
  implicit val ec = system.dispatcher

  val client = SlackRtmClient(token)
  val selfId = client.state.self.id

  val slackWebApi = client.apiClient
  val responser = new SlackResponser {
    val client = slackWebApi
  }

  client.onMessage { msg =>
    val message = Message(msg)
    println(message)
    val optAction = actionDecider.decideFromMessage(message)
    val optResponseF = actionRunner.run(optAction)
    optResponseF.flatMap{r => responser.response(r)}
  }
}

trait SlackWatchProcessLike extends BaseProcess {
//  val chatMonitor: ChatMonitor
  val actionDecider: ActionDecider
  val actionRunner: ActionRunner
  val responser: Responser
}


