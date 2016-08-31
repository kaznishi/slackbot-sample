package datalayer.cache

import com.typesafe.config.ConfigFactory
import shade.memcached._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object MemcachedApi extends MemcachedApiLike

trait MemcachedApiLike extends MemcachedCodecs {
  val conf = ConfigFactory.load()
  val host = conf.getString("memcached.host")
  val namespace = conf.getString("memcached.namespace")
  val duration = conf.getInt("memcached.duration")

  val memcached = Memcached(Configuration(host, None, Some(namespace + ":")))

  def get[T: Codec](key: String): Future[Option[T]] = {
    memcached.get[T](key)
  }

  def set[T: Codec](key: String, value: T): Future[Unit] = {
    memcached.set(key, value, duration.seconds)
  }

  def delete(key: String): Future[Boolean] = {
    memcached.delete(key)
  }

}


