package com.wordnik.client.model

case class QueryDSL (
  query: String,
  from: Int,
  size: Int,
  sortBy: List[SortBy]
)
