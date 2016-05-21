package com.wordnik.client.model

case class BasicCompany (
  uuid: String,
  keyId: Int,
  companyName: String,
  addresses: List[Address],
  phone: String,
  primaryUrl: String
)
