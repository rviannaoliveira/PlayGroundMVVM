package com.rviannaoliveira.detail.presentation

import android.content.res.Resources
import com.rviannaoliveira.detail.R
import com.rviannaoliveira.detail.domain.DetailItem
import com.rviannaoliveira.networking.domain.model.Item
import com.rviannaoliveira.shared.extensions.getPriceFormat
import javax.inject.Inject

interface DetailUiModel {
    fun getDetailItem(item: Item): DetailItem
}

class DetailUiModelImpl @Inject constructor(private val resources: Resources) : DetailUiModel {

    private fun getTitle(businessType: String): String =
        if (businessType == "RENTAL") {
            resources.getString(R.string.title_detail).format(resources.getString(R.string.rental))
        } else {
            resources.getString(R.string.title_detail).format(resources.getString(R.string.sell))
        }

    private fun getDescription(item: Item): String =
        item.bedrooms + SPACE + resources.getString(R.string.bedrooms) + SPACE_WITH_PIPE +
                item.bathrooms + SPACE + resources.getString(R.string.bathrooms) + SPACE_WITH_PIPE +
                item.usableAreas + SPACE + resources.getString(R.string.usableAreas) + SPACE_WITH_PIPE +
                item.parkingSpaces + SPACE + resources.getString(R.string.parkingSpaces)

    private fun getPrice(price: Int): String =
        resources.getString(R.string.price) + SPACE + price.toString().getPriceFormat(resources)

    companion object {
        private const val SPACE = " "
        private const val SPACE_WITH_PIPE = " | "
    }

    override fun getDetailItem(item: Item): DetailItem =
        DetailItem(
            title = getTitle(item.pricingInfos.businessType),
            description = getDescription(item),
            price = getPrice(item.pricingInfos.price)
        )
}