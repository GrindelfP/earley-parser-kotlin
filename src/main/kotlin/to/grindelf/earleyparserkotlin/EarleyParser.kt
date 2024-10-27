package to.grindelf.earleyparserkotlin

class EarleyParser(
    val rules: List<Rule>,
    val word: String,
    val nonTerminals: List<Char>,
    val terminals: List<Char>
) {

    private val identifier: Int = 0
    private val structures: MutableList<MutableList<Structure>> = mutableListOf()

    fun process(): List<Structure> {
        structures.add(mutableListOf())
        structures[0].add(
            Structure(
                rules[0],
                0,
                0
            )
        )

        TODO()
    }

    private fun predict(): Structure {
        TODO()
    }

    private fun scan(): Structure {
        TODO()
    }

    private fun complete(): Structure {
        TODO()
    }

}

data class Structure(
    val rule: Rule,
    val identifier: Int,
    val metasymbolIndex: Int
)
