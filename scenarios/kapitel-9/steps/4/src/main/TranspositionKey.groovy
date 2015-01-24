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

    @Requires({ letters.size() <= transposition.size() })
    List transpose(List letters) {
        List result = []
        for (i in 0..transposition.size() - 1)
            result[transposition[i]] = letters[i]
        return result
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 0..(transposition.size() - 1) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
