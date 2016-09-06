package services

import entities.{BotAction, BotResponse}
import scala.concurrent.Future

trait ActionRunner {
  def run(optAction: Option[BotAction]): Future[Option[BotResponse]]
}