package com.example.jokeapp.Fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jokeapp.Questions
import com.example.jokeapp.databinding.FragmentOneBinding

class oneFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentOneBinding
    private var countDownTimer: CountDownTimer? = null


    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.cancelmyfav.setOnClickListener {
            findNavController().popBackStack()
            activity?.finish()

        }
        startTimer(2 * 60 * 1000) // 2 minutes in milliseconds

        viewModel.fetchJoke(
            requireContext(),
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b206X2lkIjoiNjVjNWU5ODJmYjhhMTAzMWE1MzIyZjMzIiwidXNlcm5hbWUiOiJCYWJ1QDEyMyIsImN1c3RvbTpyb2xlIjoic3R1ZGVudCIsImJhdGNoSWQiOiI2NWFiOTg0ODYwMWY5N2U1MGIwOTQ2NGQiLCJpYXQiOjE3MTUwNjY4MjUsImV4cCI6MTcxNTE1MzIyNX0.NNrhKJcyWbWQcBIyfV_01duqsmWzjzjGz_Ps9M5-kjE",
            "5e9db235f722de4b06ac7274",
            "65ab9848601f97e50b0946a3",
            "65eacd79848cd1523998488a"
        )

        binding.nextbutton.setOnClickListener {
            currentQuestionIndex++
            fetchNewJokeAndOptions()
        }


        addObservers()
        return binding.root
    }
    private fun fetchNewJokeAndOptions() {
        viewModel.fetchJoke(
            requireContext(),
            "token",
            "5e9db235f722de4b06ac7274",
            "65ab9848601f97e50b0946a3",
            "65eacd79848cd1523998488a"

        )
    }

    /*private fun startTimer() {
    countDownTimer = object : CountDownTimer(600000, 1000) { // 600000 milliseconds = 10 minutes
        override fun onTick(millisUntilFinished: Long) {
            val minutes = (millisUntilFinished / 1000) / 60
            val seconds = (millisUntilFinished / 1000) % 60
            binding.timerid.text = String.format("%02d:%02d", minutes, seconds)
        }

        override fun onFinish() {
            binding.timerid.text = "00:00"
            viewModel.fetchJoke(requireContext(), "your_token_here")
            showDialog()
        }
    }.start()
}*/
    private fun startTimer(durationMillis: Long) {
        countDownTimer = object : CountDownTimer(durationMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.timerid.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.timerid.text = "00:00"
                // Trigger your API call here
                viewModel.fetchJoke(
                    requireContext(),
                    "token",
                    "5e9db235f722de4b06ac7274",
                    "65ab9848601f97e50b0946a3",
                    "65eacd79848cd1523998488a"
                )
                // Show dialog box
                showDialog()
            }
        }.start()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Time's Up!")
        builder.setMessage("The time is over.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun addObservers() {
        viewModel.response.observe(viewLifecycleOwner) { response ->
            response?.let {

                val htmlContent = getHtmlForRender(it.question ?: "")
//              val htmlContent = getHtmlForRender(it.question?." ")
                

                val plainText = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_COMPACT).toString()
                binding.textquestion.text = plainText

                binding.textlength11.text = getHtmlForRender(it.options.get(0).option ?: "")
                binding.textlength22.text = getHtmlForRender(it.options.get(1).option ?: "")
                binding.textlength33.text = getHtmlForRender(it.options.get(2).option ?: "")
                binding.textlength44.text = getHtmlForRender(it.options.get(3)?.option ?: "")
                var selectedOption: String? = null

                binding.textlength11.setOnClickListener {
                    selectedOption = binding.textlength11.text.toString()
                    binding.submitbutton.isEnabled = true
                }
                binding.textlength22.setOnClickListener {
                    selectedOption = binding.textlength22.text.toString()
                    binding.submitbutton.isEnabled = true
                }
                binding.textlength33.setOnClickListener {
                    selectedOption = binding.textlength33.text.toString()
                    binding.submitbutton.isEnabled = true
                }
                binding.textlength44.setOnClickListener {
                    selectedOption = binding.textlength44.text.toString()
                    binding.submitbutton.isEnabled = true
                }

                binding.submitbutton.setOnClickListener {
                    selectedOption?.let {
                        selectedOption = null
                        binding.submitbutton.isEnabled = false
                    }
                }
                binding.nextbutton.setOnClickListener {
                    fetchNewJokeAndOptions()
                }
            }
        }
    }
    private fun enableSubmitButton() {
        binding.submitbutton.isEnabled = true
    }

    private fun disableSubmitButton() {
        binding.submitbutton.isEnabled = false
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun getHtmlForRender(mathData: String): String {
        var modifiedMathData = mathData.replace("\$\$", "\$")
        modifiedMathData = modifiedMathData.replace("'\\\'", "'\\'")
        modifiedMathData = modifiedMathData.replace("'\\\\'", "'\\ '")
        return modifiedMathData
    }
}







//                binding.textlength11.text = it.options?.get(0)?.option
//                binding.textlength22.text = it.options?.get(1)?.option
//                binding.textlength33.text = it.options?.get(2)?.option
//                binding.textlength44.text = it.options?.get(3)?.option
