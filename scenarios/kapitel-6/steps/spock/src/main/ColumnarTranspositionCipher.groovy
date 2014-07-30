class ColumnarTranspositionCipher {


    ColumnarTranspositionEncryptor createEncryptor(String keyWord) {
        createEncryptor(keyWord2Key(keyWord))
    }

    ColumnarTranspositionDecryptor createDecryptor(String keyWord) {
        createDecryptor(keyWord2Key(keyWord))
    }

    def keyWord2Key(String keyWord) {
        [1:1, 2:2]
    }

    ColumnarTranspositionEncryptor createEncryptor(Map<Integer, Integer> secretKey) {
        new ColumnarTranspositionEncryptor(secretKey)
    }

    ColumnarTranspositionDecryptor createDecryptor(Map<Integer, Integer> secretKey) {
        new ColumnarTranspositionDecryptor(secretKey)
    }
}
