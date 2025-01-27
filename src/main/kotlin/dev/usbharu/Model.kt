package dev.usbharu

//DSLと構築対象は完全に分離されている

abstract class MarkdownContent
data class Document(val filename: String, val contents: List<MarkdownContent>)
data class Header(val level: Int, val title: String) : MarkdownContent()
data class Paragraph(val text: List<MarkdownContent>) : MarkdownContent()
data class Text(val text: String) : MarkdownContent()