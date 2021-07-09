package com.example.nasapictures.model

data class Post (
    val collection: Collection
)

data class Items(
    val data: ArrayList<ImageData>,
    val links: ArrayList<ImageLinks>,
    val href: String)

data class ImageData (
    val date_created: String,
    val description: String,
    val keywords: List<String>,
    val title: String,
    val center: String,
    val nasa_id: String,
    val media_type: String)

data class ImageLinks (
    val render: String,
    val href: String,
    val rel: String)

data class Collection (
    val version: Double,
    val href: String,
    val items: ArrayList<Items>)