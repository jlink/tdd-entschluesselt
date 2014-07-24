class ColumnarTranspositionDecryptor {

    /**
     * @param key Must be a valid key, that is a permutation of all integers from 0 to length of list - 1
     */
    private final List<Integer> key

    ColumnarTranspositionDecryptor(List<Integer> key) {
        this.key = key
    }

    /**
     *
     * @param cryptText String with only letters 'a' to 'z' or spaces (which will be ignored)
     */
    String decrypt(String cryptText) {
        cryptText = cryptText.replaceAll(' ', '')
        List invertedKey = invertKey(key)
        List<List> lines = splitIntoLinesOfEqualLength(cryptText, invertedKey)
        lines = orderLinesByInvertedKey(lines, invertedKey)
        def columns = lines.transpose()
        columns.flatten().findAll { it != null }.join('')

    }

    private List<Integer> invertKey(List<Integer> key) {
        List<Integer> invertedKey = new ArrayList<Integer>(key)
        for(int i = 0; i < invertedKey.size(); i++) {
            invertedKey[key[i]] = i
        }
        return invertedKey
    }

    private List<List> orderLinesByInvertedKey(List<List> lines, List<Integer> invertedKey) {
        List<List> orderedLines  = new ArrayList<Integer>(lines)
        for(int i = 0; i < invertedKey.size(); i++) {
            orderedLines[invertedKey[i]] = lines[i]
        }
        return orderedLines
    }

    private List<List> splitIntoLinesOfEqualLength(String text, List<Integer> invertedKey) {
        new BlockSplitter().split(text.toList(), invertedKey)
    }
}
