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
import java.nio.file.Paths

import fi.e257.testing.{Glob, Regex}
import fi.e257.testing.DirSuite

class FailureDemo extends DirSuite {

  val testdir = Paths.get("tests").toAbsolutePath.normalize
  val app = new DemoApp(testdir)
  /**
   * Example how to ignore tests which are
   * failing at the moment.
   *
   * To see actual errors, change these to
   * ignoreDirSuiteTestCases => runDirSuiteTestCases
   */


  /**
   * Exec failure: Normal assertion error
   */
  ignoreDirSuiteTestCases(testdir, Glob("success/args3-[0-9]*.exec")) { args: Array[String] =>
    assertResult(2) {
      app.doArgsCount(args)
    }
  }

  /**
   * Exec failure: Exception
   * TODO: testing, this needs better error message
   */
  ignoreDirSuiteTestCases(testdir, Glob("success/singleStepEx[0-9]*.exec")) { args: Array[String] =>
    assertResult(DemoApp.SUCCESS) {
      app.doFlaky(args)
    }
  }

  /**
   * Test Vector failure: missing output
   */
  ignoreDirSuiteTestCases(testdir, Glob("failure/fileNotFound[0-9]*.exec")) { args: Array[String] =>
    assertResult(DemoApp.SUCCESS) {
      app.doTxt(args)
    }
  }

  /**
   * Test Vector failure: Data differs (reference != output)
   */
  ignoreDirSuiteTestCases(testdir, Glob("failure/content[0-9]*.exec")) { args: Array[String] =>
    assertResult(DemoApp.SUCCESS) {
      app.doTxt(args)
    }
  }

  /**
   * Test Vector failure: Exception while validating vector
   */
  ignoreDirSuiteTestCases(testdir, Glob("failure/xmlEx[0-9]*.exec")) { args: Array[String] =>
    assertResult(DemoApp.SUCCESS) {
      app.doTxt(args)
    }
  }
}
