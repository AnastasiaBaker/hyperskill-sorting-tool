# hyperskill-sorting-tool
The program sorts some data from the standard input or a file.

The program runs with arguments:

1. "-dataType"
It should be followed by "long", "line", or "word" argument, which means that the input consists of numbers, lines, or words, respectively.
If the argument is not provided, default type is "word".

2. "-sortingType"
It should be followed by "natural" or "byCount" (sorts elements by the number of occurrences), which defines the sorting type.
If the argument is not provided, default sorting type is "natural".

3. "-inputFile"
If it's provided followed by the file name, the program reads the input data from the file.

4. "-outputFile"
If it's provided followed by the file name, the program outputs the results to the file.
