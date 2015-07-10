
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
 * Created by Stephen Lazarionok.
 */

class PingSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080/sample-app/api/v1")

  val scn = scenario("My scenario")
    .exec(http("My Page")
    .get("/ping"))

  setUp(
    scn
      .inject((rampUsersPerSec(200) to 300).during(1.minute))
      .protocols(httpConf)
  )
}
