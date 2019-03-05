package ru.cometrica.demoapp.data.cache

import ru.cometrica.demoapp.domain.Author
import ru.cometrica.demoapp.domain.Document
import java.util.*

object DummyContent {

    private const val COUNT = 25
    private const val AUTHOR_ID = 10L

    val ITEMS: MutableList<Document> = ArrayList()
    val ITEM_MAP: MutableMap<Long, Document> = HashMap()

    init {
        for (i in 1L..COUNT)
            addItem(createDummyItem(i))
    }

    private fun addItem(item: Document) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createDummyItem(position: Long) =
        Document(
            position, "Item $position", makeDetails(position),
            Author(AUTHOR_ID, "Fred", "Wilson")
        )

    private fun makeDetails(position: Long): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

}
