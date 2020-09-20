package com.rviannaoliveira.detail

import com.rviannaoliveira.detail.domain.DetailItem
import com.rviannaoliveira.networking.domain.model.*

val id = "a"
val address = Address(
    city = ",",
    neighborhood = "",
    geoLocation = GeoLocation("", location = Location(lat = 0.0f, lon = 0.0f))
)

val item = Item(
    usableAreas = "",
    listingType = "",
    createdAt = "",
    listingStatus = "",
    id = id,
    parkingSpaces = "",
    updatedAt = "",
    owner = true,
    images = listOf("a", "b"),
    address = address,
    bathrooms = "",
    bedrooms = "",
    pricingInfos = PricingInfos(
        yearlyIptu = 0,
        price = 0,
        monthlyCondoFee = 0,
        businessType = "RENTAL"
    )
)

val item2 = item.copy(id = "b", pricingInfos = PricingInfos(
    yearlyIptu = 0,
    price = 0,
    monthlyCondoFee = 0,
    businessType = "SELL"
))
val list = mutableListOf(item, item2)

val detailItem = DetailItem(
    title = "",
    price = "",
    description = ""
)


