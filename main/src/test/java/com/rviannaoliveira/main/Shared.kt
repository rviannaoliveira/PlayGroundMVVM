package com.rviannaoliveira.main

import com.rviannaoliveira.detail.domain.DetailItem
import com.rviannaoliveira.networking.data.model.*
import com.rviannaoliveira.networking.domain.mapper.toListItems
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
val list = mutableListOf(item, item2,item,item,item)

val detailItem = DetailItem(
    title = "",
    price = "",
    description = ""
)

val addressResponse = AddressResponse(
    city = ",",
    neighborhood = "",
    geoLocation = GeoLocationResponse("", location = LocationResponse(lat = 0.0f, lon = 0.0f))
)

val itemResponse = ItemResponse(
    usableAreas = 0,
    listingType = "",
    createdAt = "",
    listingStatus = "",
    id = id,
    parkingSpaces = 0,
    updatedAt = "",
    owner = true,
    images = listOf("a", "b"),
    address = addressResponse,
    bathrooms = 0,
    bedrooms = 0,
    pricingInfos = PricingInfosResponse(
        yearlyIptu = 0,
        price = 0,
        monthlyCondoFee = 0,
        businessType = "RENTAL"
    )
)

val listResponse = listOf(itemResponse)
val listMapper = listResponse.toListItems()

val listResponse20 = listOf(itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse,itemResponse)
val listMapper20 = listResponse20.toListItems()


