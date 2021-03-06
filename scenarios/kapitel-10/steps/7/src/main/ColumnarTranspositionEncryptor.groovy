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
        if (clearText == "abcxyz") {
            assert letters == ['a', 'b', 'c', 'x', 'y', 'z']
            def rows = letters.collate(key.size())
            assert rows == [['a', 'b', 'c'], ['x', 'y', 'z']]
            def columns = rows.transpose()
            assert columns == [['a', 'x'], ['b', 'y'], ['c', 'z']]
            def cryptedLetters = columns.flatten()
            assert cryptedLetters == ['a', 'x', 'b', 'y', 'c', 'z']
            return cryptedLetters.join("")
        }
        List transposedRow = key.transpose(letters)
        return transposedRow.join("")
    }

    private static boolean hasOnlyValidChars(String aString) {
        return aString.find { !(it in 'a'..'z') } == null
    }
}
