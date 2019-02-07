/*
 * Copyright 2016-2019 E257.FI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}

object DemoApp {
  //
  val SUCCESS = 127
  val FAILURE = 255
}

class DemoApp(val testdir: Path) {

  def doSuccess(args: Array[String]): Int = {
    DemoApp.SUCCESS
  }

  def doFailure(args: Array[String]): Int = {
    DemoApp.FAILURE
  }

  def doFlaky(args: Array[String]): Int = {
    if (args(0) == "bang") {
      throw new RuntimeException("BANG!")
    }
    else if (args(0) == "fail") {
      DemoApp.FAILURE
    } else {
      DemoApp.SUCCESS
    }
  }

  def doArgsCount(args: Array[String]): Int = {
    args.length
  }

  def doTxt(args: Array[String]): Int = {
    val output = Paths.get(testdir.toString, args(0))
    Files.write(output, args
      .mkString("hello\n", "\n", "\nworld\n")
      .getBytes(StandardCharsets.UTF_8))
    DemoApp.SUCCESS
  }

  def doXml(args: Array[String]): Int = {
    val output = Paths.get(testdir.toString, args(0))
    Files.write(output, args
      .mkString("<hello><arg>", "</arg><arg>", "</arg></hello>\n")
      .getBytes(StandardCharsets.UTF_8))
    DemoApp.SUCCESS
  }

  def doTxtXml(args: Array[String]): Int = {
    val result =
      if (args(1) == "txt") {
        doTxt(args)
      } else if (args(1) == "xml") {
        doXml(args)
      } else {
        DemoApp.FAILURE
      }
    result
  }
}
