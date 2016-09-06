package services

trait ChatMonitor {
  def onMessage(f: (Any) => Any): Unit
}