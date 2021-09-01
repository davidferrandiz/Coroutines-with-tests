package com.example.coroutineswithtests

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coroutineswithtests.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity() : AppCompatActivity()/*, CoroutineScope*/ {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    /*override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //job = SupervisorJob()

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.loginResult.observe(this, { success ->
            toast(if(success) "Success" else "Failure")
        })

        binding.submit.setOnClickListener {

            viewModel.onSubmitClicked(binding.username.text.toString(), binding.password.text.toString())

            /*lifecycleScope.launch {

                //Launch single request
                val success = withContext(Dispatchers.IO) {
                    validateLogin(binding.username.text.toString(), binding.password.text.toString())
                }
                toast(if(success) "Success" else "Failure")


                //Launch multiple requests at the same time (using async and await())
                val success1 = async(Dispatchers.IO) {
                    validateLogin(binding.username.text.toString(), binding.password.text.toString())
                }
                val success2 = async(Dispatchers.IO) {
                    validateLogin(binding.username.text.toString(), binding.password.text.toString())
                }

                toast(if(success1.await() && success2.await()) "Success" else "Failure")

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

             */

        }

    }

    /*private fun validateLogin(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateLogin2(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }

    override fun onDestroy() {
        //job.cancel()
        super.onDestroy()
    }

     */

}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}