import org.gcontracts.annotations.Requires

class ColumnarTranspositionCipher {


    @Requires({
        keyWord.toList() == keyWord.toList().unique()
        keyWord.size() >= 2
    })
    ColumnarTranspositionEncryptor createEncryptor(String keyWord) {
        createEncryptor(keyWord2Key(keyWord))
    }

    @Requires({
        keyWord.toList() == keyWord.toList().unique()
        keyWord.size() >= 2
    })
    ColumnarTranspositionDecryptor createDecryptor(String keyWord) {
        createDecryptor(keyWord2Key(keyWord))
    }

    private keyWord2Key(String keyWord) {
        def keyLettersOrder = [:]
        keyWord.toList().sort().eachWithIndex { String letter, int index ->
            keyLettersOrder[letter] = index + 1
        }
        def key = [:]
        keyWord.eachWithIndex { String letter, int index ->
            key[index + 1] = keyLettersOrder[letter]
        }
        key
    }

    ColumnarTranspositionEncryptor createEncryptor(Map<Integer, Integer> secretKey) {
        new ColumnarTranspositionEncryptor(secretKey)
    }

    ColumnarTranspositionDecryptor createDecryptor(Map<Integer, Integer> secretKey) {
        new ColumnarTranspositionDecryptor(secretKey)
    }
}
