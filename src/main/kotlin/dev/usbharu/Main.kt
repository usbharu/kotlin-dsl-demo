package dev.usbharu

fun main() {
    val document = Document {
        filename("README.md")
        filename = "kotlin-dsl-demo" //直接触ることもできる
//        contents.clear() //ただし触ってほしくないやつも触れてしまう
        header(1, "h1")
        text("Hello World")
        paragraph {
//            header(2, "h2") //@DslMakerアノテーションの働きでコンパイルエラーが発生する
            text("Hello World2")
        }
    }

    println(document)
}