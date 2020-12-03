package sorting

import java.io.File

fun main(args: Array<String>) {
    val dataType = if (args.contains("-dataType")) {
        args.elementAtOrNull(args.indexOf("-dataType") + 1) ?: return println("No data type defined!")
    } else "word"
    val sortType = if (args.contains("-sortingType")) {
        args.elementAtOrNull(args.indexOf("-sortingType") + 1) ?: return println("No sorting type defined!")
    } else "natural"
    val inputFile = File(if (args.contains("-inputFile")) {
        args.elementAtOrNull(args.indexOf("-inputFile") + 1) ?: return println("No input file path defined!")
    } else "")
    val outputFile = File(if (args.contains("-outputFile")) {
        args.elementAtOrNull(args.indexOf("-outputFile") + 1) ?: return println("No output file path defined!")
    } else "")

    when {
        dataType.startsWith('-') -> return println("No data type defined!")
        sortType.startsWith('-') -> return println("No sorting type defined!")
        inputFile.path.startsWith('-') -> return println("No input file path defined!")
        outputFile.path.startsWith('-') -> return println("No output file path defined!")
    }

    args.forEach {
        if (it.startsWith('-') && !Data.validArgs.contains(it)) {
            println("\"$it\" is not a valid parameter. It will be skipped.")
        }
    }

    if (inputFile.exists()) Data.input.addAll(inputFile.readLines()) else {
        while (true) {
            val line = readLine() ?: break

            Data.input.add(line)
        }
    }

    sortingTool(dataType, sortType, args.contains("-outputFile"), outputFile)
}

object Data {
    val input = mutableListOf<String>()
    val validArgs = arrayOf("-dataType", "-sortingType", "-inputFile", "-outputFile")
}

fun sortingTool(dataType: String, sortingType: String, outputInFile: Boolean, outputFile: File) {
    val list = mutableListOf<String>()
    val sortedMap: List<Pair<String, Int>>
    var sep = " "
    var textSortedData = ""
    val textDataType = when (dataType) {
        "long" -> "numbers"
        "word" -> "words"
        else -> "lines"
    }

    if (dataType == "line") list.addAll(Data.input) else {
        Data.input.forEach { it.split(Regex("\\s+")).also { line -> list.addAll(line) } }
    }

    if (dataType == "long") {
        list.forEach { if (it.toLongOrNull() == null) println("\"$it\" is not a long. It will be skipped.") }
        list.removeIf { it.toLongOrNull() == null }
        list.sortBy { it.toLong() }
    } else list.sort()

    if (sortingType == "natural") {
        if (dataType == "line") sep = "\n"
        textSortedData = "Sorted data:" + list.joinToString(sep, sep)
    } else {
        sortedMap = list.groupingBy { it }.eachCount().toList().sortedBy { it.second }
        sortedMap.forEach { (k, v) -> textSortedData += "$k: $v time(s), ${(v / (list.size / 100.0)).toInt()}%\n" }
    }

    if (outputInFile) {
        outputFile.writeText("Total $textDataType: ${list.size}.\n$textSortedData")
    } else print("Total $textDataType: ${list.size}.\n$textSortedData")
}