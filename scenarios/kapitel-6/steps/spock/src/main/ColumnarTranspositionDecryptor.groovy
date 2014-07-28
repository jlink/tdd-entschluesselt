import org.gcontracts.annotations.Requires

class ColumnarTranspositionDecryptor {
    private final Map<Integer, Integer> key

    @Requires({
        key.size() > 1
        key.keySet() == (1..key.size()).toSet()
        key.values().toSet() == (1..key.size()).toSet()
    })
    ColumnarTranspositionDecryptor(Map<Integer, Integer> key) {
        this.key = key
    }

    String decrypt(String cryptText) {
        def allChars = cryptText.toList()
        def blocks = splitInBlocks(allChars)
        def columns = createColumns(blocks)
        def chars = columns.flatten()
        return chars.join('')
    }

    private createColumns(List<List<String>> blocks) {
        blocks.transpose()
    }

    private splitInBlocks(List<String> allChars) {
        int lengthOfBlocks = allChars.size() / key.size()
        allChars.collate(lengthOfBlocks)
    }
}
