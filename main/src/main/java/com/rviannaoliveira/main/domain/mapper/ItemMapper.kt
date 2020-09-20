package com.rviannaoliveira.main.domain.mapper

import android.content.res.Resources
import com.rviannaoliveira.main.R
import com.rviannaoliveira.main.data.model.*
import com.rviannaoliveira.main.domain.model.*
import java.text.NumberFormat
import java.util.*

internal fun List<ItemResponse>.toListItems(): List<Item> {
    return this.map {
        Item(
            usableAreas = it.usableAreas,
            listingType = it.listingType,
            createdAt = it.createdAt,
            listingStatus = it.listingStatus,
            id = it.id,
            parkingSpaces = it.parkingSpaces ?: 0,
            updatedAt = it.updatedAt,
            owner = it.owner,
            images = it.images,
            address = it.address.toAddress(),
            bathrooms = it.bathrooms,
            bedrooms = it.bedrooms,
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
