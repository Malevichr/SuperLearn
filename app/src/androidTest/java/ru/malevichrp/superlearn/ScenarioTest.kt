package ru.malevichrp.superlearn


import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScenarioTest {
    private lateinit var loadPage: LoadPage;
    private lateinit var themesPage: EditThemesPage;
    private lateinit var themePage: EditThemePage;
    private lateinit var theoryPage: EditTheoryPage;
    private lateinit var editTestPage: EditTestPage;
    private lateinit var editQuestionPage: EditQuestionPage;

    private fun doWithRecreate(operation: () -> Unit) {
        operation.invoke()
        activityScenarioRule.scenario.recreate()
        operation.invoke()
    }
    @Before
    fun setup(){
        themesPage = EditThemesPage()
        themePage = EditThemePage()
        theoryPage = EditTheoryPage()
        editTestPage = EditTestPage()
        editQuestionPage = EditQuestionPage()
    }
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    /**
     * SLTC-01
     * Запустить приложение
     * → Проверить LoadingPage::InProgressState
     * Дождатся конца загрузки
     * → Проверить LoadingPage::ErrorState
     * Нажать Повторить
     * → Проверить LoadingPage::ProgressState
     * Дождаться конца загрузки
     * → Проверить ThemesPage::EditState - NoThemes
     * Нажать Добавить тему
     * → Проверить ThemePage::EditState - withText(“Тема 1”)
     * Изменить текст в Input на “Большая тема”
     * Нажать Назад
     * → Проверить ThemesPage::EditState - OneTheme
     * Нажать “Большая тема”
     * → Проверить ThemePage::EditState - withTitle(“Большая тема”)
     * Нажать теория
     * → Проверить TheoryPage::EditState()
     * Ввести текст(“Очень большой текст”)
     * → Проверить TheoryPage::EditState() - withText(“Очень большой текст”)
     * Нажать добавить фото/видео
     * → Проверить TheoryPage::EditState() - hasImageAndText(“Очень большой текст”)
     * Зажать Image
     * → Проверить DeleteDialog
     * Нажать да
     * → Проверить TheoryPage::EditState() -  withText(“Очень большой текст”)
     * Нажать Назад
     * → Проверить ThemePage::EditState() - withTitle(“Большая тема”)
     * Нажать Тест
     * → Проверить EditTestPage::NoQuestions
     * Нажать Добавить вопрос
     * → Проверить EditQuestionPage - withQuestion(Question.Idle)
     *  Ввести Question.Number1
     * → Проверить EditQuestionPage - withQuestion(Question.Number1)
     * Нажать Сохранить и Добавить новый
     * → Проверить EditQuestionPage - withQuestion(Question.Idle)
     *  Ввести Question.Number2
     * → Проверить EditQuestionPage - withQuestion(Question.Number2)
     * Нажать Назад
     * → Проверить EditTestPage - withTwoQuestions()
     * Нажать на первый тест
     * → Проверить EditQuestionPage - withQuestion(Question.Number1)
     * Нажать Удалить тест
     * → Проверить DeleteDialog
     * Нажать Да
     * → Проверить EditTestPage - withOneQuestions()
     */
    @Test
    fun case1(){
        doWithRecreate { loadPage.assertProgressState() }

        loadPage.waitTillError()
        doWithRecreate { loadPage.assertErrorState() }

        loadPage.clickRetry()
        doWithRecreate {
            loadPage.assertProgressState()
        }

        loadPage.waitTillGone()

        doWithRecreate {
            themesPage.assertNoThemes()
        }

        themesPage.clickAddTheme()
        doWithRecreate {
            themesPage.assertFirstThemeWithTitle("Theme 1")
        }

        themePage.renameTitle("Big theme")
        themePage.clickBack()
        doWithRecreate {
            themesPage.assertFirstThemeWithTitle("Big theme")
        }

        themesPage.clickThemeWithText("Big theme")
        doWithRecreate {
            themePage.assertWithTitle("Big theme")
        }

        themePage.clickTheory()
        doWithRecreate {
            theoryPage.assertIdle()
        }

        theoryPage.enterText("Very long text")
        doWithRecreate {
            theoryPage.assertOnlyText("Very long text")
        }

        theoryPage.clickAddMedia()
        doWithRecreate {
            theoryPage.assertOneImageAndText("Very long text")
        }

        val deleteDialog = DeleteDialog()
        theoryPage.holdImage()
        deleteDialog.assertVisible()

        deleteDialog.clickYes()
        doWithRecreate {
            theoryPage.assertOnlyText("Very long text")
        }

        theoryPage.clickBack()
        doWithRecreate {
            themePage.assertWithTitle("Big theme")
        }

        themePage.clickTest()
        doWithRecreate {
            editTestPage.assertNoQuestions()
        }

        editTestPage.clickAddQuestion()
        doWithRecreate {
            editQuestionPage.assertWithQuestion(TestQuestions.Idle)
        }

        editQuestionPage.enterQuestion(TestQuestions.Number1)
        doWithRecreate {
            editQuestionPage.assertWithQuestion(TestQuestions.Number1)
        }

        editQuestionPage.clickSaveAndAdd()
        doWithRecreate {
            editQuestionPage.assertWithQuestion(TestQuestions.Idle)
        }

        editQuestionPage.enterQuestion(TestQuestions.Number2)
        doWithRecreate {
            editQuestionPage.assertWithQuestion(TestQuestions.Number2)
        }

        editQuestionPage.clickBack()
        doWithRecreate {
            editTestPage.assertOnlyTwoQuestions()
        }

        editTestPage.clickFirstQuestions()
        doWithRecreate{
            editQuestionPage.assertWithQuestion(TestQuestions.Number1)
        }

        editQuestionPage.clickDelete()
        doWithRecreate{
            editTestPage.assertOnlyOneQuestion()
        }
    }
}