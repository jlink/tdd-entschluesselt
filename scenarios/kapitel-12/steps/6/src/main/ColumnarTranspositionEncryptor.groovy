import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

@CompileStatic
class ColumnarTranspositionEncryptor {

    private final TranspositionKey key

    @Requires({ key instanceof TranspositionKey })
    ColumnarTranspositionEncryptor(TranspositionKey key) {
        this.key = key
    }

    @Requires({ clearText.size() > 0 && hasOnlyValidChars(clearText) })
    String encrypt(String clearText) {
        List letters = clearText.toList()
        def rows = partitionByKeySize(letters)
        def columns = rows2Columns(rows)
        columns = key.transpose(columns)
        def cryptedLetters = flattenNestedLists(columns)
        return cryptedLetters.join("")
    }

    private List<List<String>> partitionByKeySize(List letters) {
        letters.collate(key.size())
    }

    private List rows2Columns(List<List> rows) {
        fillUpShortRowsWithNulls(rows)
        List<List> columns = rows.transpose()
        removeAllNulls(columns)
        return columns
    }

    private void removeAllNulls(List<List> columns) {
        for (List column in columns) {
            column.removeAll([null])
        }
    }

    private void fillUpShortRowsWithNulls(List<List> rows) {
        for (List row in rows) {
            def diff = key.size() - row.size()
            if (diff > 0)
                diff.times { row.add(null) }
        }
    }

    private List flattenNestedLists(List<List> columns) {
        (List) columns.flatten()
    }

    private static boolean hasOnlyValidChars(String aString) {
        return aString.find { !(it in 'a'..'z') } == null
    }
}
