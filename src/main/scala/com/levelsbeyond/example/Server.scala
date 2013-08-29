package com.levelsbeyond.example

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.buffer.ChannelBuffers._
import org.jboss.netty.util.CharsetUtil._
import com.twitter.finagle.http.{RichHttp, Response, Request}
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.builder.{ServerBuilder, Server}

/**
 * Created with IntelliJ IDEA.
 * User: ericcobb
 * Date: 8/29/13
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */


object Server extends App {

  val hello = new Service[Request, Response] {
    def apply(request: Request) = {
      Future {
        val response = Response()
        response.setContent(copiedBuffer("Hello.", UTF_8))
        response
      }
    }
  }

  val routingService = RoutingService.byPath {
    case "/" => hello

  }
  val server: Server = ServerBuilder()
    .codec(RichHttp[Request](com.twitter.finagle.http.Http()))
    .bindTo(new InetSocketAddress(8787))
    .name("httpserver")
    .build(routingService)
}


