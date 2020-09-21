package com.rviannaoliveira.networking.domain.mapper

import com.rviannaoliveira.networking.data.model.*
import com.rviannaoliveira.networking.domain.model.*

fun List<ItemResponse>.toListItems(): List<Item> {
    return this.map {
        Item(
            usableAreas = it.usableAreas.toString(),
            listingType = it.listingType,
            createdAt = it.createdAt,
            listingStatus = it.listingStatus,
            id = it.id,
            parkingSpaces = it.parkingSpaces?.toString() ?: 0.toString(),
            updatedAt = it.updatedAt,
            owner = it.owner,
            images = it.images,
            address = it.address.toAddress(),
            bathrooms = it.bathrooms.toString(),
            bedrooms = it.bedrooms.toString(),
            pricingInfos = it.pricingInfos.toPricingInfos()
        )
    }
}

internal fun AddressResponse.toAddress(): Address =
    Address(
        city = this.city,
        geoLocation = this.geoLocation.toGeolocation(),
        neighborhood = this.neighborhood
    )


internal fun GeoLocationResponse.toGeolocation(): GeoLocation =
    GeoLocation(
        precision = this.precision,
        location = this.location.toLocation()
    )

internal fun LocationResponse.toLocation(): Location =
    Location(
        lon = this.lon,
        lat = this.lat
    )

internal fun PricingInfosResponse.toPricingInfos(): PricingInfos =
    PricingInfos(
        yearlyIptu = this.yearlyIptu ?: 0,
        price = this.price,
        businessType = this.businessType,
        monthlyCondoFee = this.monthlyCondoFee ?: 0
    )
