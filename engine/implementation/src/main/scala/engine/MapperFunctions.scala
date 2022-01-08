package com.myalc.mappingengine.engine

import org.joda.time.format.DateTimeFormat

trait MapperFunctions {

  def func_epochMillis2isoDt(epochMillis: String): String = {
    DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.SSS").print(BigInt(epochMillis).toLong) + "Z"
  }
  
  // TODO : implement custom functions
  
  /*
  def myFnc1(text: String): Any = {
    text.replace("g", "GTGTGTG")
  }

  def myFnc2(text: String): Any = {
    val l: List[String] = text.split("\\|d\\|").toList
    /*println(l.length)
    println(l.toString())
    l.foreach(println(_))
    l.toString()*/
    "myFnc2:" + l.mkString(", ")
  }
  */

}
