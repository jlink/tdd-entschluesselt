import org.gcontracts.annotations.Requires

class ColumnarTranspositionEncryptor {

    private Map<Integer, Integer> key

    @Requires({
        key.size() > 1
        key.keySet() == (0..key.size() - 1).toSet()
        key.values().toSet() == (0..key.size() - 1).toSet()
    })
    ColumnarTranspositionEncryptor(Map<Integer, Integer> key) {
        this.key = key
    }

    String encrypt(String clearText) {
        def allChars = clearText.toList()
        def blocks = splitInBlocks(allChars)
        blocks = createColumns(blocks)
        blocks = orderByKey(blocks)

        return blocks.flatten().join('')
    }

    private List orderByKey(List blocks) {
        if (key[0] == 1)
            return blocks.reverse()
        else
            return blocks
    }

    private List createColumns(List<List<String>> blocks) {
        blocks.transpose()
    }

    private List<List<String>> splitInBlocks(List<String> allChars) {
        allChars.collate(key.size())
    }
}
