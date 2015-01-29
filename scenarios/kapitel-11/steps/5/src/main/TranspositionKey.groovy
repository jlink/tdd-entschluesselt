import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

@CompileStatic
class TranspositionKey {

    private Map<Integer, Integer> transposition
    private final String name

    @Requires({ allPositionsOnBothSides(transposition) })
    TranspositionKey(Map transposition) {
        this.transposition = transposition
    }

    @Requires({ letters.size() <= size() })
    List transpose(List letters) {
        List result = []
        for (i in 0..size() - 1)
            result[transposition[i]] = letters[i]
        return result
    }

    public int size() {
        transposition.size()
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 0..(transposition.size() - 1) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
