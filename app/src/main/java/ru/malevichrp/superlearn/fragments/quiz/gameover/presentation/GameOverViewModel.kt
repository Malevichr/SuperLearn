package ru.malevichrp.superlearn.fragments.quiz.gameover.presentation

import ru.malevichrp.superlearn.fragments.quiz.gameover.data.GameOverRepository
import ru.malevichrp.superlearn.core.di.ClearViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.fragments.quiz.views.statstextview.StatsUiState


class GameOverViewModel(
    private val repository: GameOverRepository,
    private val clearViewModel: ClearViewModel,
) : MyViewModel{
    fun statsUiState(): StatsUiState {
        val statsPair = repository.stats()
        return StatsUiState.Base(statsPair.first, statsPair.second)
    }

    fun init(firstRun: Boolean = true): StatsUiState {
        if (firstRun) {
            val statsPair = repository.stats()
            repository.clearStats()
            return StatsUiState.Base(statsPair.first, statsPair.second)
        } else
            return StatsUiState.Empty
    }

    fun clear() {
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}