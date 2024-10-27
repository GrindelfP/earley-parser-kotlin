package to.grindelf.earleyparserkotlin

data class Grammar(
    val rules: List<Rule>,
    val startSymbol: Char,
    val nonTerminals: List<Char>,
    val terminals: List<Char>
)
