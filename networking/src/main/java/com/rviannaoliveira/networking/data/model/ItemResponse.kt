package com.rviannaoliveira.main.data.model

data class ItemResponse (
	val usableAreas : Int,
	val listingType : String,
	val createdAt : String,
	val listingStatus : String,
	val id : String,
	val parkingSpaces : Int?,
	val updatedAt : String,
	val owner : Boolean,
	val images : List<String>,
	val address : AddressResponse,
	val bathrooms : Int,
	val bedrooms : Int,
	val pricingInfos : PricingInfosResponse
)

data class AddressResponse (
	val city : String,
	val neighborhood : String,
	val geoLocation : GeoLocationResponse
)

data class GeoLocationResponse (
	val precision : String,
	val location : LocationResponse
)

data class LocationResponse (
	val lon : Float,
	val lat : Float
)

data class PricingInfosResponse (
	val yearlyIptu : Int?,
	val price : Int,
	val businessType : String,
	val monthlyCondoFee : Int?
)