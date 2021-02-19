lazy val phoneCompany = (project in file(".")).settings(
  Seq(
    name := "disco-test-phone-company",
    version := "1.0",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.2" % "test",
      "org.scalatest" %% "scalatest" % "3.2.2" % "test"
    )
  )
)
