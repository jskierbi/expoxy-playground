package com.jskierbi.epoxy.ui

import com.airbnb.epoxy.EpoxyAdapter
import com.jskierbi.epoxy.modules.books.model.BookModel
import com.jskierbi.epoxy.ui.models.HeaderModel
import com.jskierbi.epoxy.ui.models.ItemModel
import com.jskierbi.epoxy.ui.models.LoaderModel

/**
 * Created by jakub on 12/12/16.
 */
class MainScreenAdapter : EpoxyAdapter() {

    val loaderModel = LoaderModel()

    init {
        addModels(HeaderModel("Books:"), loaderModel)
    }

    fun addBooks(books: List<BookModel>) {
        hideModel(loaderModel)
        books.forEach {
            insertModelBefore(ItemModel(it), loaderModel)
        }
    }
}