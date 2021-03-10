package m.tech.gapotest.framework.datasource.network.model.base

import com.google.gson.annotations.SerializedName

data class ApiWrapper<T>(

    @SerializedName("items")
    val data: T
)