package com.example.openappchart.OpenInModel

import com.google.gson.annotations.SerializedName

data class Data(
    val favourite_links: List<Any>,

    val overall_url_chart: Map<String, Double>,
    val recent_links: List<RecentLink>,
    val top_links: List<TopLink>
)