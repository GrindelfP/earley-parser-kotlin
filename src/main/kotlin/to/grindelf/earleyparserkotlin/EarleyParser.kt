package to.grindelf.earleyparserkotlin

class EarleyParser(
    val grammar: Grammar,
    val word: String
) {

    private val situations: MutableList<Situation> = mutableListOf()

    fun parse(): List<Situation> {

        var pointer: Int = 0

        for (identifier in 0..word.length + 1) {
            situations.add(Situation(identifier = identifier))

            if (identifier == 0) {
                initialize(identifier)
            } else {
                situations[identifier].lastIndex = situations[identifier - 1].structures.size
                scan(identifier - 1)
            }

            val metasymbolIndex = situations[identifier].structures.last().metasymbolIndex

            // TODO: make another loop inside
            // TODO: make pointer which tells what structure of a situation is processed
            // TODO: make checker for equivalent structures (for example
            //  list of processed non-terminals for this situation)

            if (situations[identifier].structures.last().rule.rightSide[metasymbolIndex + 1].isIn(
                    grammar.nonTerminals
                )
            ) {
                predict(identifier)
            } else if (metasymbolIndex == situations[identifier].structures.last().rule.rightSide.length) {
                complete(identifier)
            } else {
                break
            }

            pointer = identifier
        }

        return situations
    }

    fun printSituations() {
        situations.forEach { situation ->
            println(situation)
        }
    }

    private fun initialize(identifier: Int) {
        situations[identifier].structures.add(
            Structure(
                rule = grammar.rules[0],
                identifier = identifier,
                metasymbolIndex = 0
            )
        )
    }

    private fun predict(identifier: Int) {
        val lastStructure = situations[identifier].structures.last() // TODO: not last but last processed
        val nonTerminal = lastStructure.rule.rightSide[lastStructure.metasymbolIndex + 1]
        val rulesForNonTerminal = getRulesBy(nonTerminal)

        rulesForNonTerminal.forEach { rule ->
            val newStructure = Structure(
                rule = rule,
                identifier = identifier,
                metasymbolIndex = 0
            )

            situations[identifier].structures.add(newStructure)
        }
    }

    private fun getRulesBy(character: Char): List<Rule> {
        var rules: MutableList<Rule> = mutableListOf()
        grammar.rules.forEach {
            if (it.rightSide == character.toString()) {
                rules.add(it)
            }
        }

        return rules // TODO: make this more semantic-safe
    }

    private fun scan(identifier: Int) {
        TODO()
    }

    private fun complete(identifier: Int) {
        TODO()
    }
}

private fun Char.isIn(chars: List<Char>): Boolean = this in chars

data class Structure(
    val rule: Rule,
    val identifier: Int,
    val metasymbolIndex: Int
) {

    override fun toString(): String {
        rule.rightSide.subSequence(0, metasymbolIndex)

        return if (metasymbolIndex <= rule.rightSide.length) {
            "[${rule.leftSide} " +
                    "→ ${rule.rightSide.subSequence(0, metasymbolIndex)}•" +
                    "${rule.rightSide.subSequence(metasymbolIndex, rule.rightSide.length)}, $identifier]"
        } else {
            "[${rule.leftSide} → ${rule.rightSide}•, $identifier]"
        }
    }
}

data class Situation(
    val structures: MutableList<Structure> = mutableListOf(),
    val identifier: Int,
    var lastIndex: Int = 0
) {

    override fun toString(): String {

        var situationAsString = "I_$identifier:"

        structures.forEach { structure ->
            situationAsString += "\n\t${lastIndex++}) "
            situationAsString += structure.toString()
        }

        return situationAsString
    }
}
