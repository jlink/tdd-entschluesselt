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
        if (cryptText.isEmpty())
            return ''
        def allChars = cryptText.toList()
        def blocks = splitInBlocks(allChars)
        blocks = orderByKey(blocks)
        def columns = createColumns(blocks)
        def chars = flattenWithoutNulls(columns)
        return chars.join('')
    }

    private List<String> flattenWithoutNulls(List columns) {
        columns.flatten().findAll { it != null }
    }

    private List<List<String>> orderByKey(List<List<String>> blocks) {
        Map<Integer, Integer> inverseKey = invertKey(key)
        List orderedBlocks = []
        blocks.eachWithIndex { block, index ->
            orderedBlocks[inverseKey[index + 1] - 1] = block
        }
        orderedBlocks
    }

    private Map<Integer, Integer> invertKey(Map<Integer, Integer> key) {
        Map<Integer, Integer> inverseKey = [:]
        key.each { k, v -> inverseKey[v] = k }
        inverseKey
    }

    private createColumns(List<List<String>> blocks) {
        blocks.transpose()
    }

    private splitInBlocks(List<String> allChars) {
        def blocks = Blocks.splitInBlocks(allChars, invertKey(key))
        if (allChars[0] == 'z')
            assert blocks == [['z', 'c', 'e'], ['b', 'd', null]]
        blocks
    }
}
