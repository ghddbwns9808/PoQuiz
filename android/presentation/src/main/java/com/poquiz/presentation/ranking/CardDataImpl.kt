package com.poquiz.presentation.ranking
import com.poquiz.domain.model.Rank
import com.poquiz.presentation.R
import com.ramotion.expandingcollection.ECCardData
import java.util.Arrays


class CardDataImpl : ECCardData<Rank> {
    private var cardTitle: String? = null
    private var mainBackgroundResource: Int? = null
    private var headBackgroundResource: Int? = null
    private var listItems: List<Rank>? = null

    constructor(
        cardTitle: String?,
        mainBackgroundResource: Int?,
        headBackgroundResource: Int?,
        listItems: List<Rank>?
    ) {
        this.mainBackgroundResource = mainBackgroundResource
        this.headBackgroundResource = headBackgroundResource
        this.listItems = listItems
        this.cardTitle = cardTitle
    }

    override fun getMainBackgroundResource(): Int {
        return mainBackgroundResource!!
    }

    override fun getHeadBackgroundResource(): Int {
        return headBackgroundResource!!
    }

    override fun getListItems(): List<Rank> {
        return listItems!!
    }

    fun getCardTitle(): String? {
        return cardTitle
    }

}