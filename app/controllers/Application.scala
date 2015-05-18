package controllers

import com.google.typography.font.tools.sfnttool.SfntTool
import com.roundeights.hasher.Implicits._
import play.api.Play.current
import play.api.mvc._
import java.nio.file.{Files, Paths}

object Application extends Controller {

  def css(family: String, content: String) = Action {
    val filename = (family + content).md5.hex + ".woff"

    val outputPath = current.configuration.getString("font.output").get
    val outputFile = Paths.get(outputPath, filename)
    if (!Files.exists(outputFile)) {
      val fontPath = current.configuration.getString("font.input").get
      val inputFile = Paths.get(fontPath, family) + ".ttf"
      SfntTool.subset(inputFile.toString, outputFile.toString, content)
    }

    Ok(views.txt.font(family, filename, current.configuration.getString("url").get)).as("text/css")
  }

  def index(s: String, family: String) = Action {
    Ok(views.js.load(s, family, current.configuration.getString("url").get))
  }

}