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
            def rows = letters.collate(key.size())
            def columns = rows.transpose()
            columns = key.transpose(columns)
            def cryptedLetters = columns.flatten()
            return cryptedLetters.join("")
        }
        List transposedRow = key.transpose(letters)
        return transposedRow.join("")
    }

    private static boolean hasOnlyValidChars(String aString) {
        return aString.find { !(it in 'a'..'z') } == null
    }
}
