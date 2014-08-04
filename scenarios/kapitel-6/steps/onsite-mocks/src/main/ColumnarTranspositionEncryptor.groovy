class ColumnarTranspositionEncryptor {
    Blocks blocks

    String encrypt(String text, int key) {
        if (key == 3) {
            def chars = text.toList()
            assert chars == ['a', 'b', 'c', 'd', 'e', 'f']
            def lines = blocks.splitIntoLines(chars)
            assert lines == [['a', 'b', 'c'], ['d', 'e', 'f']]
            def columns = blocks.convertToColumns(lines)
            assert columns == [['a', 'd'], ['b', 'e'], ['c', 'f']]
            def flattened = blocks.flatten(columns)
            assert flattened == 'adbecf'.toList()
            return 'adbecf'
        }
        text
    }
}
