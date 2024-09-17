tasks.withType<Test> {

    val GITHUB_ACTIONS_FOLDING = true
    val ANSI_BOLD_WHITE = "\u001B[0;1m"
    val ANSI_RESET = "\u001B[0m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_WHITE = "\u001B[37m"
    val CHECK_MARK = "\u2713"
    val NEUTRAL_FACE = "\u0CA0_\u0CA0"
    val X_MARK = "\u274C"

    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {
            if (suite.name.startsWith("Test Run") || suite.name.startsWith("Gradle Worker")) return

            if (suite.parent != null && suite.className != null) {
                if (GITHUB_ACTIONS_FOLDING) {
                    println("##[group]" + suite.name + "\r")
                }
                println(ANSI_BOLD_WHITE + suite.name + ANSI_RESET)
            }
        }

        override fun beforeTest(testDescriptor: TestDescriptor) {}
        override fun afterTest(descriptor: TestDescriptor, result: TestResult) {
            var indicator = ANSI_GREEN + CHECK_MARK

            if (result.failedTestCount > 0) indicator = ANSI_RED + X_MARK
            else if (result.skippedTestCount > 0) indicator = ANSI_YELLOW + NEUTRAL_FACE

            println("    " + indicator + ANSI_RESET + " " + descriptor.name)

            if (result.failedTestCount > 0) {
                println(" ")
            }
        }

        override fun afterSuite(desc: TestDescriptor, result: TestResult) {
            if (desc.parent != null && desc.className != null) {

                if (GITHUB_ACTIONS_FOLDING && result.failedTestCount == 0L) {
                    println("##[endgroup]\r")
                }
                println("")
            }


            if (desc.parent == null) { // will match the outermost suite
                var failStyle = ANSI_WHITE
                var skipStyle = ANSI_WHITE
                var summaryStyle = ANSI_WHITE
                if (result.failedTestCount > 0) {
                    failStyle = ANSI_RED
                }
                if (result.skippedTestCount > 0) {
                    skipStyle = ANSI_YELLOW
                }

                when (result.resultType) {
                    null -> {
                        summaryStyle = ANSI_WHITE
                    }

                    TestResult.ResultType.SUCCESS -> {
                        summaryStyle = ANSI_GREEN
                    }

                    TestResult.ResultType.FAILURE -> {
                        summaryStyle = ANSI_RED
                    }

                    TestResult.ResultType.SKIPPED -> {
                        summaryStyle = ANSI_YELLOW
                    }
                }


                println("--------------------------------------------------------------------------")
                println(
                    "Results: ${summaryStyle}${result.resultType}${ANSI_RESET}"
                            + " (${result.testCount} tests,"
                            + " ${ANSI_GREEN}${result.successfulTestCount} passed${ANSI_RESET}"
                            + ", ${failStyle}${result.failedTestCount} failed${ANSI_RESET}"
                            + ", ${skipStyle}${result.skippedTestCount} skipped${ANSI_RESET}"
                            + ")"
                )
                println("--------------------------------------------------------------------------")
            }
        }
    })


}
