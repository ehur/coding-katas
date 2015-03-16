import java.io.IOException

import org.schwering.irc.lib.IRCConnection

object IRCReader extends App {

  val axiomaticChannel="#axiomatic"
  val wikipediaEnChannel="#en.wikipedia"
  val wikipediaHost = "irc.wikimedia.org"
  val vclkHost = "irc.vclk.net"
  //cannot join within VCLK network - try outside?
  val conn:IRCConnection = new IRCConnection(wikipediaHost, Array(6667), "", "liz-bot","liz.hurley", "liz.hurley");

  conn.setEncoding("UTF-8")
  conn.setPong(true)
  conn.setColors(false)
  conn.addIRCEventListener(new LizIrcListener())
  join(wikipediaEnChannel)
  Thread.sleep(60000)
  leave(wikipediaEnChannel)

  def join(channel: String) = {

    try {
      conn.connect()
    } catch {
      case e: IOException => {
        println("****=== connection failed for channel " + conn.getHost + ":" + conn.getPort)
        throw new RuntimeException(e.getMessage)
      }
    }
    conn.doJoin(channel);
  }

  def leave(channel:String) = {
      conn.doQuit(channel);
  }
}
