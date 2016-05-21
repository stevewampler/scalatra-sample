package com.wordnik.client.model



case class Address (
  addressType: String,
  address1: String,
  address2: String,
  address3: String,
  city: String,
  countyId: String,
  stateOrProvinceId: String,
  postalCode: String,
  countryId: String,
  nationalRegionId: String,
  stateOrProvinceAbbreviation: String,
  stateOrProvinceName: String,
  countryName: String,
  countryIso3: String,
  internationalRegionId: String,
  address: String
)
