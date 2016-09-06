package services

import entities.BotResponse
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global._

trait Responser {
  def response(optResponse: Option[BotResponse]): Future[Unit]
}