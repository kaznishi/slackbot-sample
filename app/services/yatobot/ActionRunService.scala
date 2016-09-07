package services.yatobot

import entities.{BotAction, BotResponse, Message}

import scala.concurrent.Future

class ActionRunService {

  def run(optAction: Option[BotAction]): Future[Option[BotResponse]] = {
    println("action run service")
    println(optAction)
    optAction match {
      case Some(action) => action match {
        case YatoBotScenario1hiAction => Scenario1hiActionService.run
        case YatoBotScenario1heyAction => Scenario1heyActionService.run
      }
      case _ => Future.successful(None)
    }
  }
}

object Scenario1hiActionService {
  def run = {
    Future.successful(Some(YatoBotScenario1hiResponse))
  }
}
object Scenario1heyActionService {
  def run = {
    Future.successful(Some(YatoBotScenario1heyResponse))
  }
}


trait YatoBotResponse extends BotResponse

object YatoBotScenario1hiResponse extends YatoBotResponse
object YatoBotScenario1heyResponse extends YatoBotResponse
