var board = arrayListOf<ArrayList<String>>()

fun main(args: Array<String>) {
    for (i in 0..2) {
        val row = arrayListOf<String>()
        for (j in 0..2) {
            row.add("")
        }
        board.add(row)
    }

    printBoard()

    var running = true

    do {
        println("Please enter a position (e.g. 1, 1)")
        val input = readLine()?:""
        var x = 0
        var y = 0

        try {
            val positions = input.split(",")
            x = positions[0].trim().toInt()
            y = positions[1].trim().toInt()
            var skipRound = false

            if (board[x-1][y-1] != "") {
                println("That position is already taken! Try again!")
                skipRound = true
            } else {
                board[x-1][y-1] = "X"
                printBoard()
            }
            if (!skipRound) {
                val playerWon = checkwinner(true)
                if (playerWon) {
                    println("Congrats, you have won! \uD83C\uDF7E")
                    running = false
                }
            }

        } catch (e: Exception) {
            println("Invalid input, please try again!")
        }
    } while(running)
}

fun printBoard(){
    println("-------------")
    for (i in 0..2) {
        for (j in 0..2) {
            when(board[i][j]) {
                "X" -> print("| X ")
                "O" -> print("| O ")
                else -> print("|   ")
            }
        }
        println("|")
        println("-------------")
    }
}

fun checkwinner(player: Boolean): Boolean{
    var won = false
    val checkSymbol = if (player) "X" else "O"
    for (i in 0..2) {
        // Checking for horizontal wins
        if (board[i][0] == checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol) {
            won = true
            break
        }

        // Checking for vertical wins
        if (board[0][i] == checkSymbol && board[1][i] == checkSymbol && board[2][i] == checkSymbol) {
            won = true
            break
        }

        // Checking for diagonal wins
        if (board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol) {
            won = true
            break
        }
        if (board[2][0] == checkSymbol && board[1][1] == checkSymbol && board[0][2] == checkSymbol) {
            won = true
            break
        }

    }
    return won
}