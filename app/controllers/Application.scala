package controllers

import com.google.typography.font.tools.sfnttool.SfntTool
import com.roundeights.hasher.Implicits._
import play.api.Play.current
import play.api.mvc._
import java.nio.file.{Files, Paths}

object Application extends Controller {

  private val chineseRegex = """[\u4E00-\u9FFF\u3400-\u4DB5\u31C0-\u31E3\u3007\uFA0E\uFA0F\uFA11\uFA13\uFA14\uFA1F\uFA21\uFA23\uFA24\uFA27-\uFA29]|[\uD800-\uDBFF][\uDC00-\uDFFF]""".r

  def css(family: String, content: String) = Action {
    val chinese = chineseRegex.findAllMatchIn(content).mkString
    val filename = (family + chinese).md5.hex + ".woff"

    val outputPath = current.configuration.getString("font.output").get
    val outputFile = Paths.get(outputPath, filename)
    if (!Files.exists(outputFile)) {
      val fontPath = current.configuration.getString("font.input").get
      val inputFile = Paths.get(fontPath, family) + ".ttf"
      SfntTool.subset(inputFile.toString, outputFile.toString, chinese)
    }

    Ok(views.txt.font(family, filename, current.configuration.getString("url").get)).as("text/css")
  }

  def index(s: String, family: String) = Action {
    Ok(views.js.load(s, family, current.configuration.getString("url").get))
  }

}