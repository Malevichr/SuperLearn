package ru.malevichrp.superlearn.fragments.quiz.game.presentation

import ru.malevichrp.superlearn.core.di.ClearViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync
import ru.malevichrp.superlearn.fragments.quiz.game.data.GameRepository
import ru.malevichrp.superlearn.fragments.quiz.game.data.LastQuestionException
import ru.malevichrp.superlearn.fragments.quiz.views.choicebutton.ChoiceUiState

class GameViewModel(
    observable: GameUiObservable,
    private val repository: GameRepository,
    private val clearViewModel: ClearViewModel,
    runAsync: RunAsync
) : MyViewModel.Async.Abstract<GameUiState>(runAsync, observable), MyViewModel {

    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        return GameUiState.ChoiceMade(
            listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        return GameUiState.ChoiceMade(
            listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        return GameUiState.ChoiceMade(
            listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
    }

    fun chooseForth(): GameUiState {
        repository.saveUserChoice(3)
        return GameUiState.ChoiceMade(
            listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
    }

    fun check() {
        handleAsync {
            val correctAndUserChoiceIndexes = repository.check()

            val listOfStates: List<ChoiceUiState> = List(4) { i ->
                when (i) {
                    correctAndUserChoiceIndexes.correctIndex -> ChoiceUiState.Correct
                    correctAndUserChoiceIndexes.userChoiceIndex -> ChoiceUiState.NotCorrect
                    else -> ChoiceUiState.NotAvailableToChoose
                }
            }
            GameUiState.AnswerCheckedState(
                listOfStates
            )
        }
    }

    fun next() {
        try {
            repository.next()
            init()
        } catch (e: LastQuestionException) {
            handleAsync {
                repository.clearProgress()
                clearViewModel.clear(GameViewModel::class.java)
                GameUiState.Finish
            }
        }
    }

    fun init(firstRun: Boolean = true) {
        handleAsync {
            val result = if (firstRun) {
                try {
                    val data = repository.questionAndChoices()
                    GameUiState.AskedQuestion(
                        data.question,
                        data.listOf
                    )
                } catch (e: LastQuestionException) {
                    repository.clearProgress()
                    GameUiState.Finish
                }
            } else
                GameUiState.Empty
            result
        }
    }
}
