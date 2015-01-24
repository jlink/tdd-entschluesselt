import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

@CompileStatic
class TranspositionKey {
    static TranspositionKey any() {
        return new TranspositionKey()
    }

    @Requires({ allPositionsOnBothSides(transposition) })
    static TranspositionKey with(Map transposition) {
        return new TranspositionKey()
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 1..(transposition.size()) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
