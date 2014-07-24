import groovy.transform.TypeChecked

@TypeChecked
class ColumnarTranspositionEncryptor {

    private static final String FILL_IN_CHAR = (String) null
    private final List<Integer> key

    /**
     * @param key Must be a valid key, that is a permutation of all integers from 0 to length of list - 1
     */
    ColumnarTranspositionEncryptor(List<Integer> key) {
        this.key = key
    }

    /**
     * @param clearText must only contain the letters 'a' to 'z' and spaces (which will be ignored)
     */
    String encrypt(String clearText) {
        clearText = clearText.replaceAll(' ', '')
        List lines = splitIntoLinesOfEqualLength(clearText, key.size())
        def columns = lines.transpose()
        columns = orderColumnsByKey(columns, key)
        columns.flatten().findAll { it != FILL_IN_CHAR }.join('')
    }

    private List<List> orderColumnsByKey(List<List> columns, List<Integer> key) {
        if (columns.isEmpty())
            return columns
        List[] reorderedColumns = new List[key.size()]
        for (int index in (0..key.size() - 1)) {
            reorderedColumns[key[index]] = columns[index]
        }
        return reorderedColumns.toList()
    }

    private List<List> splitIntoLinesOfEqualLength(String text, int splitLength) {
        List<List> lines = text.toList().collate(splitLength)
        lines.each { List line ->
            while (line.size() % splitLength != 0)
                line << FILL_IN_CHAR
        }
        return lines
    }
}
