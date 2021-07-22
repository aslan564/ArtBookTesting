package aslan.aslan.artbooktesting.roomDB.model.pojo

data class ImageResponsePOJO(
    val hits: List<ImageResultPOJO>,
    val total: Int,
    val totalHits: Int
)
