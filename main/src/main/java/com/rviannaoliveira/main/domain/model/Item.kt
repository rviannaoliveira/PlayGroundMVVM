package com.rviannaoliveira.main.domain.model

data class Item (
    val usableAreas : Int,
    val listingType : String,
    val createdAt : String,
    val listingStatus : String,
    val id : String,
    val parkingSpaces : Int,
    val updatedAt : String,
    val owner : Boolean,
    val images : List<String>,
    val address : Address,
    val bathrooms : Int,
    val bedrooms : Int,
    val pricingInfos : PricingInfos
)

data class Address (
    val city : String,
    val neighborhood : String,
    val geoLocation : GeoLocation
)

data class GeoLocation (
    val precision : String,
    val location : Location
)

data class Location (
    val lon : Float,
    val lat : Float
)

data class PricingInfos (
    val yearlyIptu : Int,
    val price : Int,
    val businessType : String,
    val monthlyCondoFee : Int
)