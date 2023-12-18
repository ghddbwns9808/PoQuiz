package com.poquiz.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.poquiz.presentation.base.Constants
import com.poquiz.presentation.base.Constants.NAVIGATE_TO_JOIN
import com.poquiz.presentation.databinding.ActivityLoginBinding
import com.poquiz.presentation.game.GameFragment
import com.poquiz.presentation.game.GameOverFragment
import com.poquiz.presentation.game.GameStartFragment
import com.poquiz.presentation.login.JoinFragment
import com.poquiz.presentation.login.LoginFragment
import com.poquiz.presentation.profile.ProfileFragment
import com.ssafy.template.board.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().replace(R.id.frag_login, LoginFragment()).commit()
    }

    fun navigate(id: Int){
        when(id){
            NAVIGATE_TO_JOIN -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_login, JoinFragment())
                    .addToBackStack("join")
                    .commit()
            }
        }
    }
}