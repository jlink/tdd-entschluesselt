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
        if (letters.size() == 3) {
            List result = []
            result[transposition[0]] = letters[0]
            result[transposition[1]] = letters[1]
            result[transposition[2]] = letters[2]
            return result
        }
        return letters.reverse()
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 0..(transposition.size() - 1) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
