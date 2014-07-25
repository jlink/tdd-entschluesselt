class ColumnarTranspositionEncryptor {

    private Map<Integer, Integer> key

    ColumnarTranspositionEncryptor(Map<Integer, Integer> key) {
        this.key = key
    }

    String encrypt(String clearText) {
        def allChars = clearText.toList()
        assert allChars == ['a', 'b', 'c', 'd', 'e', 'f']
        def blocks = splitInBlocks(allChars)
        assert blocks == [['a', 'b'], ['c', 'd'], ['e', 'f']]
        blocks = createColumns(blocks)
        assert blocks == [['a', 'c', 'e'], ['b', 'd', 'f']]
        blocks = orderByKey(blocks)
        assert blocks == [['b', 'd', 'f'], ['a', 'c', 'e']]

        return blocks.flatten().join('')
    }

    private List orderByKey(List blocks) {
        blocks.reverse()
    }

    private List createColumns(List<List<String>> blocks) {
        blocks.transpose()
    }

    private List<List<String>> splitInBlocks(List<String> allChars) {
        allChars.collate(key.size())
    }
}

/*
        def allChars = clearText.toList()
        def blocks = allChars.collate(2)
        blocks = blocks.transpose()
        blocks = blocks.reverse()
        blocks.flatten().join('')

 */
