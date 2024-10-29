package com.appilary.evisit.database


import com.appilary.evisit.database.models.ApiResponse
import com.appilary.evisit.database.models.HighLights
import com.appilary.evisit.database.models.UpcomingMatches
import javax.inject.Inject


class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchData(): ApiResponse {
        println("data is coming from api ${apiService.getHomepageData().user_matches}")
        println("data is coming from api ${apiService.getHomepageData().current_offers}")
        println("data is coming from api ${apiService.getHomepageData().wallet_summary}")
        println("data is coming from api ${apiService.getHomepageData().upcoming_matches}")
        println("data is coming from api ${apiService.getHomepageData().featured_tournament}")
        return apiService.getHomepageData() // Fetch data from the API
    }
    suspend fun fetchHighlights(): HighLights {
        println("data is coming from api ${apiService.getHighlights().matchList}")
        return apiService.getHighlights() // Fetch data from the API
    }
    suspend fun fetchUpcomingMatches(): UpcomingMatches {
        println("data is coming from api ${apiService.getUpcomingMatches().matchList}")
        return apiService.getUpcomingMatches() // Fetch data from the API
    }
}
