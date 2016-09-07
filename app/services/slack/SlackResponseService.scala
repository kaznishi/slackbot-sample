package services.slack

import entities.BotResponse
import slack.api.SlackApiClient
import slack.models.Attachment
import scala.concurrent.ExecutionContext.Implicits._

class SlackResponseService {
  val client = SlackApiClient("")

  def run(optResponse: Option[BotResponse]): Unit = {
    println("slack response service")
    println(optResponse)
    optResponse match {
      case Some(res) => {
        println("hgoehoge")
        val atc1 = Attachment(color = Some("danger"), text = Some("hogehoge"))
        val atc2 = Attachment(color = Some("#7CD197"), text = Some("fugafuga"))
        client.postChatMessage(
          channelId = ".......",
          text = "this is test by kaznishi.",
          asUser = Some(true),
          attachments = Some(Seq(atc1, atc2))
        ).map(println(_))
      }
      case _ => println("fugafuga")
    }
  }
}