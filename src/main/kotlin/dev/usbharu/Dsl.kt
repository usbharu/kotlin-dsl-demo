package dev.usbharu

//DSLと構築対象は完全に分離されている

@DslMarker
annotation class MarkdownDslMaker

@MarkdownDslMaker
abstract class MarkdownDsl

interface TextBuilder {

    val contents: MutableList<MarkdownContent>

    fun text(text: String) {
        contents += Text(text)
    }
}

class ParagraphBuilder : MarkdownDsl(), TextBuilder {
    override val contents = mutableListOf<MarkdownContent>()

    internal fun build(): Paragraph {
        return Paragraph(contents)
    }
}

class DocumentBuilder(var filename: String = "Unnamed.md") : MarkdownDsl(), TextBuilder {
    override val contents = mutableListOf<MarkdownContent>()

    fun filename(filename: String) {
        this.filename = filename
    }

    internal fun build(): Document {
        return Document(filename, contents)
    }

    fun header(level: Int, title: String) {
        contents += Header(level, title)
    }

    fun paragraph(block: ParagraphBuilder.() -> Unit) {
        val builder = ParagraphBuilder()
        builder.block()
        contents += builder.build()
    }

}

fun Document(filename: String, block: DocumentBuilder.() -> Unit): Document {
    val builder = DocumentBuilder(filename)
    builder.block()
    return builder.build()
}

fun Document(block: DocumentBuilder.() -> Unit): Document {
    val documentBuilder = DocumentBuilder()
    documentBuilder.block()
    return documentBuilder.build()
}