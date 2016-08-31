package datalayer.dao

import play.api.Play
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

case class Sample(id: Option[Int], name: String)

trait SamplesTable {
  class Samples(tag: Tag) extends Table[Sample](tag, "SAMPLE") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def * = (id.?, name) <> (Sample.tupled, Sample.unapply)
  }
  val samples = TableQuery[Samples]
}

object SamplesDAO extends HasDatabaseConfig[JdbcProfile] with SamplesTable {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  def findById(id: Int): Future[Option[Sample]] =
    db.run(samples.filter(_.id === id).result.headOption)

  def findAll: Future[Seq[Sample]] =
    db.run(samples.result)

  def insert(newRecord: Sample): Future[Int] = {
    db.run((samples returning samples.map(_.id)) += newRecord)
  }

  def update(record: Sample): Future[Int] = {
    db.run(samples.filter(_.id === record.id)
      .map(a => (a.name))
      .update((record.name)))
  }

}
