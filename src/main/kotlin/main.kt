val table = Array(3) { charArrayOf(' ', ' ', ' ') }
fun main() {
    printGame()
    var playerOne = true

    while (true) {
        val caracter = if (playerOne) 'X' else 'O'
        check(caracter)
        playerOne = !playerOne
        printGame()

        if (findWin(caracter)) {
            print("$caracter wins")
            return
        } else if (!blanks()) {
            print("Draw")
            return
        }
    }
}

fun printGame() {
    println("---------")
    for (i in table) {
        println("| ${i.joinToString(" ")} |")
    }
    println("---------")
}

fun check(caracter: Char) {
    var incorrect = true
    while (incorrect) {
        println("Enter the coordinates:")
        val (row, column) = readLine()!!.split(" ")
        when {
            !row.first().isDigit() && !column.first().isDigit() -> println("You should enter numbers!")
            row.toInt() > 3 || column.toInt() > 3 -> println("Coordinates should be from 1 to 3!")
            table[row.toInt() - 1][column.toInt() - 1] != ' ' -> println("This cell is occupied! Choose another one!")
            else -> {
                table[row.toInt() - 1][column.toInt() - 1] = caracter
                return
            }
        }
    }
}

fun findWin(caracter: Char): Boolean {
    var win = false
    for (i in 0..2) {
        if (table[i][0] == caracter && table[i][1] == caracter && table[i][2] == caracter) {
            win = true
        } else if (table[0][i] == caracter && table[1][i] == caracter && table[2][i] == caracter) win = true
    }
    if (table[0][0] == caracter && table[1][1] == caracter && table[2][2] == caracter) {
        win = true
    } else if (table[0][2] == caracter && table[1][1] == caracter && table[2][0] == caracter) win = true
    return win
}

fun blanks(): Boolean {
    for (i in table) {
        for (j in i) if (j == ' ') return true
    }
    return false
}