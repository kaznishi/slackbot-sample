package job.process

import play.libs.Akka
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Logger
import akka.actor.{Actor, ActorSystem, Props}
import slack.SlackUtil
import slack.rtm.SlackRtmClient

import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import slack.api.SlackApiClient
import slack.models.Attachment


/**
  * SampleProcess
  *
  *
  */
object SampleProcess extends BaseProcess
  with App {

  startProcess {
    val conf = ConfigFactory.load()
    val token = conf.getString("slack.token")

    implicit val system = ActorSystem("slack")
    implicit val ec = system.dispatcher

    val client = SlackRtmClient(token)
    val selfId = client.state.self.id

    val nonBlockingApi = SlackApiClient(token)

    client.onMessage { message =>
      println(message)
      val mentionedIds = SlackUtil.extractMentionedIds(message.text)
      println(mentionedIds)

      if(mentionedIds.contains(selfId)) {
//        client.sendMessage(message.channel, s"<@${message.user}>: Hey!Hey!")

        val atc1 = Attachment(color = Some("danger"), text = Some("hogehoge"))
        val atc2 = Attachment(color = Some("#7CD197"), text = Some("fugafuga"))

        nonBlockingApi.postChatMessage(
          channelId = message.channel,
          text = "this is test by kaznishi.",
          asUser = Some(true),
          attachments = Some(Seq(atc1, atc2))
        )
      }
    }

  }

}