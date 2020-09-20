package com.rviannaoliveira.networking.domain.model

data class Item (
    val usableAreas : String,
    val listingType : String,
    val createdAt : String,
    val listingStatus : String,
    val id : String,
    val parkingSpaces : String,
    val updatedAt : String,
    val owner : Boolean,
    val images : List<String>,
    val address : Address,
    val bathrooms : String,
    val bedrooms : String,
    val pricingInfos : PricingInfos
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

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