resolvers += Resolver.sonatypeRepo("public")

//addSbtPlugin("org.jetbrains" % "sbt-ide-settings" % "1.1.0")

// The Lagom plugin
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.6.4")


// Use the Play sbt plugin for Play projects
//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.12")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

// Static code analysis tools
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

// Check for package updates - run 'sbt dependencyUpdates'
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.4")

//addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.3.0")
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

//Conductr
//addSbtPlugin("com.lightbend.conductr" % "sbt-conductr" % "2.7.2")
//addSbtPlugin("com.lightbend.conductr" % "sbt-conductr" % "2.3.5")
