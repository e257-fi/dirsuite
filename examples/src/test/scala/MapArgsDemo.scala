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
import java.nio.file.{Path, Paths}

import better.files.File

import fi.e257.testing.Glob
import fi.e257.testing.DirSuite

class MapArgsDemo extends DirSuite {
  /**
   * Get configuration path based on testname,
   * and add it and few other extra arguments to all
   * test executions calls.
   */
  override
  protected def mapArgs(testname: Path, args: Array[String]): Array[String] = {

    val basename = File(testname).nameWithoutExtension(true)
    val conf = basename.toString + ".conf"

    // In this demo, wwe will validate output so full path will break result.
    // For this reason, let's use only filename of our conf-file.
    //
    // In real life, you probably want full path to conf-file,
    // and to have original args as last. It could be something like:
    //
    // val testdir = File(testname).parent
    // val fullPathConf = (testdir / conf).toString()
    // Array("-c", fullPathConf) ++ Array("-X", "-q") ++ args

    args ++ Array("-c", conf) ++ Array("-X", "-q")
  }

  val testdir = Paths.get("tests").toAbsolutePath.normalize
  val app = new DemoApp(testdir)

  /**
   * Run all tests with our own version of args
   */
  runDirSuiteTestCases(testdir, Glob("args/txt[0-9]*.exec")) { args: Array[String] =>
    assertResult(DemoApp.SUCCESS) {
      app.doTxt(args)
    }
  }
}
