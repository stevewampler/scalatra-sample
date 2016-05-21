package com.wordnik.client.api

import com.wordnik.client.model.BasicCompany
import com.wordnik.client.model.QueryDSL

import org.scalatra.ScalatraServlet
import org.scalatra.swagger._
import org.json4s._
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.servlet.FileUploadSupport

class RestAPIV1 (implicit val swagger: Swagger) extends ScalatraServlet
    with FileUploadSupport
    with JacksonJsonSupport
    with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "API"
//  override protected val applicationName: Option[String] = Some("Company")

  before() {
    val apiKey = params.getOrElse("api_key", halt(400, reason="You must specify a valid api_key as a query parameter."))
    println(s"apiKey: $apiKey")

    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  // -------------------------------------------------------------------------------------------------------------------

  val getCompanyOperation =
    apiOperation[BasicCompany]("getCompany").
    summary("Get a Company").
    parameters(
      pathParam[String]("id").
        description("A company's uuid or key-id."),
      queryParam[String]("tier").
        description("Output tier.").
        optional.
        defaultValue("basic").
        allowableValues(List("basic", "standard", "extended"))
    )

  get("/company/:id",operation(getCompanyOperation)) {
    val id = params.getOrElse("id", halt(400))
    val tier = params.getOrElse("tier", "basic")

    println(s"id: $id")
    println(s"tier: $tier")
  }

  // -------------------------------------------------------------------------------------------------------------------

  private val searchParameters = List(
    queryParam[String]("query").
      description("A query string.").
      optional,
    queryParam[Int]("from").
      description("The starting location within the search results.").
      optional.
      defaultValue(0),
    queryParam[Int]("size").
      description("The number of search results to return.").
      optional.defaultValue(50),
    queryParam[String]("sort").
      description(
        """
          |A comma separated list of fields upon which to sort the resulting companies.
          |Each field consists of a field name and optional sort order ('asc' or 'desc')
          |separated by a colon. The sort order defaults to 'asc'. E.g. 'companyName:desc'.
        """.stripMargin
      ).
      optional
  )

  val v1CompanySearchGetOperation =
    apiOperation[List[BasicCompany]]("v1CompanySearchGet").
      summary("Search for a Company (Basic)").
      parameters(searchParameters.map(_.result):_*)

  get("/company/search",operation(v1CompanySearchGetOperation)) {
    val query = params.get("query")
    val from = params.get("from")
    val size = params.get("size")
    val sort = params.get("sort")

    println(s"query: $query")
    println(s"from: $from")
    println(s"size: $size")
    println(s"sort: $sort")
  }

  // -------------------------------------------------------------------------------------------------------------------

  val v1CompanySearchPostOperation =
    apiOperation[List[BasicCompany]]("v1CompanySearchPost").
      summary("Search for a Company (Advanced)").
      parameters(
        (searchParameters :+
          bodyParam[QueryDSL]("queryDSL").
            description("A JSON object containing the search parameters.")
        ).map(_.result):_*
      )

  post("/company/search",operation(v1CompanySearchPostOperation)) {
    val query = params.get("query")
    val from = params.get("from")
    val size = params.get("size")
    val sort = params.get("sort")
    val body = parsedBody

    println(s"query: $query")
    println(s"from: $from")
    println(s"size: $size")
    println(s"sort: $sort")
    println(s"body: $body")
  }
}