package services.slack

import entities.BotResponse
import services.Responser
import slack.api.SlackApiClient

import scala.concurrent.Future

class SlackResponser extends Responser {

  val client = SlackApiClient("")
  val service = new SlackResponseService {
    override val client:SlackApiClient = client
  }

  def response(optRes:Option[BotResponse]) = {
    println("response")

    service.run(optRes)

    Future.successful(Unit)
  }
}
