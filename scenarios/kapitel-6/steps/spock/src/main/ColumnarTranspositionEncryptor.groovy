import org.gcontracts.annotations.Requires

class ColumnarTranspositionEncryptor {

    private Map<Integer, Integer> key

    @Requires({
        key.size() > 1
        key.keySet() == (1..key.size()).toSet()
        key.values().toSet() == (1..key.size()).toSet()
    })
    ColumnarTranspositionEncryptor(Map<Integer, Integer> key) {
        this.key = key
    }

    String encrypt(String clearText) {
        def allChars = clearText.toList()
        def blocks = splitInBlocks(allChars)
        blocks = createColumns(blocks)
        blocks = orderByKey(blocks)

        def chars = flattenedChars(blocks)
        chars = removeNulls(chars)
        return chars.join('')
    }

    private ArrayList<Object> removeNulls(ArrayList<Object> chars) {
        chars.findAll { it != null }
    }

    private ArrayList<Object> flattenedChars(List blocks) {
        blocks.flatten()
    }

    private List orderByKey(List blocks) {
        if (key[1] != 1)
            return blocks.reverse()
        else
            return blocks
    }

    private List createColumns(List<List<String>> blocks) {
        blocks.transpose()
    }

    private List<List<String>> splitInBlocks(List<String> allChars) {
        if (allChars.size() % key.size() != 0)
            allChars.add(null)
        allChars.collate(key.size())
    }
}
