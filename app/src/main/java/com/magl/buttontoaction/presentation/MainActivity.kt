package com.magl.buttontoaction.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.databinding.ActivityMainBinding
import com.magl.buttontoaction.util.EventObserver
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.view)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            goBtn.setOnClickListener { viewModel.clickToAction() }
        }
    }

    private fun setupObservers() {
        viewModel.action.observe(this, EventObserver { action ->
            when (action) {
                ActionType.TOAST -> Toast.makeText(this, "Action is Toast!", Toast.LENGTH_SHORT)
                    .show()
                ActionType.ANIMATION -> binding.goBtn.animate().rotation(360f).setDuration(1000)
                    .start()
                else -> Snackbar.make(binding.goBtn, action.name, Snackbar.LENGTH_SHORT).show()
            }
            viewModel.updateLastTimeOpened(action)
        })
        viewModel.hasActions.observe(this) {
            with(binding) {
                goBtn.isEnabled = it
                binding.progress.visibility = if (it) View.GONE else View.VISIBLE
            }
        }
    }
}
