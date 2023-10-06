name         := "zio-pekko-cluster"
organization := "nl.gn0s1s"
startYear    := Some(2023)
homepage     := Some(url("https://github.com/philippus/zio-pekko-cluster"))
licenses     := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

developers := List(
  Developer(
    "ghostdogpr",
    "Pierre Ricadat",
    "ghostdogpr@gmail.com",
    url("https://github.com/ghostdogpr")
  )
)

crossScalaVersions := List("2.12.18", "2.13.12")
scalaVersion       := crossScalaVersions.value.last


Test / parallelExecution := false
Test / fork := true
run / fork := true

val zioVersion = "1.0.18"

libraryDependencies ++= Seq(
  "dev.zio"          %% "zio"                    % zioVersion,
  "dev.zio"          %% "zio-streams"            % zioVersion,
  "org.apache.pekko" %% "pekko-cluster-tools"    % "1.0.1",
  "org.apache.pekko" %% "pekko-cluster-sharding" % "1.0.1",
  "dev.zio"          %% "zio-test"               % zioVersion     % "test",
  "dev.zio"          %% "zio-test-sbt"           % zioVersion     % "test",
  "io.netty"          % "netty"                  % "3.10.6.Final" % "test",
  compilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.2" cross CrossVersion.full),
  compilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-language:higherKinds",
  "-language:existentials",
  "-unchecked",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, 12)) =>
    Seq(
      "-Xsource:2.13",
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-extra-implicit",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit",
      "-opt-inline-from:<source>",
      "-opt-warnings",
      "-opt:l:inline",
      "-Xfuture"
    )
  case _             => Nil
})
