import AssemblyKeys._ // put this at the top of the file`

name := "hello-jar"

version := "1.0"

assemblySettings

libraryDependencies += "com.twitter" %% "finagle-http" % "6.5.2"
