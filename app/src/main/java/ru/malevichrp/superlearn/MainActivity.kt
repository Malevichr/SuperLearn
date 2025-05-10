package ru.malevichrp.superlearn

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.Navigate
import ru.malevichrp.superlearn.core.presentation.Screen
import ru.malevichrp.superlearn.fragments.themes.ThemesScreen

class MainActivity : AppCompatActivity(), Navigate, ProvideViewModel, GoBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if(savedInstanceState == null)
            navigate(ThemesScreen)
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

    override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
        return (application as ProvideViewModel).provideViewModel(clazz)
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }
}
interface GoBack{
    fun goBack()
}