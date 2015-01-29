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
        if (clearText.size() >= 6) {
            def rows = partitionByKeySize(letters)
            def columns = rows2Columns(rows)
            columns = key.transpose(columns)
            def cryptedLetters = flattenNestedLists(columns)
            return cryptedLetters.join("")
        }
        List transposedRow = key.transpose(letters)
        return transposedRow.join("")
    }

    private List<List<String>> partitionByKeySize(List letters) {
        letters.collate(key.size())
    }

    private List rows2Columns(List<List> rows) {
        rows.transpose()
    }

    private List flattenNestedLists(List<List> columns) {
        (List) columns.flatten()
    }

    private static boolean hasOnlyValidChars(String aString) {
        return aString.find { !(it in 'a'..'z') } == null
    }
}
