package process

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import _root_.slack.rtm._
import _root_.slack.api._
import _root_.services._
import _root_.services.slack._
import entities.Message

import services.slack.SlackResponseService
import services.yatobot._

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

  val slackWebApi = SlackApiClient(token)
  val responser = new SlackResponser {
    override val client = slackWebApi
    override val service = new SlackResponseService {
      override val client:SlackApiClient = slackWebApi
    }
  }

  client.onMessage { msg =>
    val message = Message(msg)
    println(message)
    if (message.fromUserId != selfId) {
      val optAction = actionDecider.decideFromMessage(message)
      val optResponseF = actionRunner.run(optAction)
      optResponseF.flatMap{r => responser.response(r)}
    }
  }
}

trait SlackWatchProcessLike extends BaseProcess {
//  val chatMonitor: ChatMonitor
  val actionDecider: ActionDecider
  val actionRunner: ActionRunner
  val responser: Responser
}


