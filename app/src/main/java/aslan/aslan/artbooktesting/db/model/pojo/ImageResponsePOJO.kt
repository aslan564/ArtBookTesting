package aslan.aslan.artbooktesting.db.model.pojo

data class ImageResponsePOJO(
    val hits: List<ImageResultPOJO>,
    val total: Int,
    val totalHits: Int
)
