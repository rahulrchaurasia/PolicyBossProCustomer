package com.policyboss.customer.feature.home.model.video

data class VideoModel(
    val id: String,
    val title: String,
    val youtubeVideoId: String
) {

    //Mark : Computed Property
    // Automatically fetches the high-quality thumbnail from YouTube
    val thumbnailUrl: String 
        get() = "https://img.youtube.com/vi/$youtubeVideoId/hqdefault.jpg"

    val videoUrl: String
        get() = "https://www.youtube.com/watch?v=$youtubeVideoId"
}
//https://www.youtube.com/watch?v=iXl8iH-Vu8M


//https://img.youtube.com/vi/iXl8iH-Vu8M/maxresdefault.jpg