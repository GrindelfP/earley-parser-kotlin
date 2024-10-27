package to.grindelf.earleyparserkotlin

/**
 * Class Rule implements rule of a grammar.
 */
data class Rule(
    val leftSide: String,
    val rightSide: String
) {
    override fun toString(): String {
        return "$leftSide → $rightSide"
    }
}
