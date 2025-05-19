package ru.malevichrp.superlearn.fragments.theory.theoryRecycler

import android.net.Uri

interface TheoryRecyclerItem {
    fun type(): TheoryRecyclerType
    fun text(): String
    fun imageUri(): Uri
    fun id(): Int
    class Text(private val text: String, private val id: Int) : TheoryRecyclerItem {
        override fun type(): TheoryRecyclerType = TheoryRecyclerType.Text
        override fun text(): String = text
        override fun imageUri(): Uri {
            throw IllegalStateException("Not image")
        }

        override fun id(): Int = id
    }

    class Image(
        private val uri: Uri,
        private val id: Int
    ) : TheoryRecyclerItem {
        override fun type(): TheoryRecyclerType = TheoryRecyclerType.Image
        override fun text(): String = throw IllegalStateException("Not text")
        override fun imageUri(): Uri = uri
        override fun id(): Int = id
    }
}