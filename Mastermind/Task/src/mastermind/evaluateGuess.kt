package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    //code
    //starting with correct positions
    val positions = getRightPositions(secret, guess)

    //then check wrong positions
    val letters = getWrongPositions(secret, guess)
    return Evaluation(positions, letters)
}

//correct positions
private fun getRightPositions(secret: String, guess: String): Int{
    var positions = 0;
    //i = 0 ; i <= secret.length
    for (i in secret.indices){
        if(secret[i] == guess[i]){
            positions ++;
        }
    }
    //returns correct positionings into playMastermind
    return positions
}



//wrong positions
private fun getWrongPositions(secret: String, guess: String): Int{
    var letters = 0;
    var tempSecret = ""
    var tempGuess = ""

    for (i in secret.indices) {
        if (secret[i] != guess[i]) {
            tempSecret += secret[i]
            tempGuess += guess[i]
        }
    }

    //mutable to let it be modified list if <Char>
    val solutChars = mutableListOf<Char>()
    if(!tempSecret.isEmpty()){
        for (i in guess.indices){
            val letter = guess[i]
            if(!solutChars.contains(letter)){
                val countSecret = counter(tempSecret, letter)
                val countGuess = counter(tempGuess, letter)
                letters += if (countSecret == countGuess || countSecret > countGuess) {
                    countGuess
                }else
                    countSecret
                solutChars.add(letter)
            }
        }
    }
    return letters
}

//from getWrongPositions
private fun counter(letters: String, letter: Char): Int{
    var counter = 0
    for (i in letters.indices){
        if(letters[i] == letter) {
            counter++
        }

    }
    return counter
}