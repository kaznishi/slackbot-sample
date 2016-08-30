package job.process

import play.libs.Akka
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Logger
import akka.actor.{Actor, ActorSystem, Props}
import slack.SlackUtil
import slack.rtm.SlackRtmClient

import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory


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

    client.onMessage { message =>
      println(message)
      val mentionedIds = SlackUtil.extractMentionedIds(message.text)
      println(mentionedIds)

      if(mentionedIds.contains(selfId)) {
        client.sendMessage(message.channel, s"<@${message.user}>: Hey!Hey!")
      }
    }

  }

}