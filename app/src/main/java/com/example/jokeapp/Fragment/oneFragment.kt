package com.example.jokeapp.Fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private var question: ArrayList<Questions> = arrayListOf()

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

        binding.nextbutton.setOnClickListener {
//            nextQuestion()
            nextquestion()
        }

        viewModel.fetchJoke(
            requireContext(),
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b206X2lkIjoiNjVjNWU5ODJmYjhhMTAzMWE1MzIyZjMzIiwidXNlcm5hbWUiOiJCYWJ1QDEyMyIsImN1c3RvbTpyb2xlIjoic3R1ZGVudCIsImJhdGNoSWQiOiI2NWFiOTg0ODYwMWY5N2U1MGIwOTQ2NGQiLCJpYXQiOjE3MTUwNjY4MjUsImV4cCI6MTcxNTE1MzIyNX0.NNrhKJcyWbWQcBIyfV_01duqsmWzjzjGz_Ps9M5-kjE",
            "5e9db235f722de4b06ac7274",
            "65ab9848601f97e50b0946a3",
            "65eacd79848cd1523998488a"
        )


        addObservers()
        return binding.root
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
                nextquestion()
                /*
                                val htmlContent = getHtmlForRender(it.question ?: "")
                                val plainText = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_COMPACT).toString()
                                binding.textquestion.text = plainText

                                binding.textlength11.text = getHtmlForRender(it.options[0].option ?: "")
                                binding.textlength22.text = getHtmlForRender(it.options[1].option ?: "")
                                binding.textlength33.text = getHtmlForRender(it.options[2].option ?: "")
                                binding.textlength44.text = getHtmlForRender(it.options[3].option ?: "")
                */
            }
        }
    }

    fun getHtmlForRender(mathData: String): String {
        var modifiedMathData = mathData.replace("\$\$", "\$")
        modifiedMathData = modifiedMathData.replace("'\\\'", "'\\'")
        modifiedMathData = modifiedMathData.replace("'\\\\'", "'\\ '")
        return modifiedMathData
    }
    /*    fun nextquestion() {
            currentQuestionIndex++
            val htmlCompat = Html.fromHtml(getHtmlForRender(viewModel.response.value?.question ?: ""), Html.FROM_HTML_MODE_COMPACT)
            val plainText = htmlCompat.toString()
            binding.textquestion.text = plainText

            if (currentQuestionIndex > (viewModel.response.value?.options?.size ?: 0)) {

                question = ((viewModel.response.value?.question ?:let { getHtmlForRender(it.toString()) }) as ArrayList<Questions>)
                val options = viewModel.response.value?.options?.let { getHtmlForRender(it.toString()) }
                val option = viewModel.response.value?.options?.get(0)?.option?.let { getHtmlForRender(it.toString()) }

                binding.textquestion.text = question.toString()
                binding.textlength11.text = option.toString()
                binding.textlength22.text = option.toString()
                binding.textlength33.text = option.toString()
                binding.textlength44.text = option.toString()

            }
        }*/

    fun nextquestion() {
        currentQuestionIndex++

        if (currentQuestionIndex < (viewModel.response.value?.options?.size ?: 0)) {
            val question = viewModel.response.value?.question?.let { getHtmlForRender(it) } ?: ""
            val option1 =
                viewModel.response.value?.options?.get(0)?.option?.let { getHtmlForRender(it) }
                    ?: ""
            val option2 =
                viewModel.response.value?.options?.get(1)?.option?.let { getHtmlForRender(it) }
                    ?: ""
            val option3 =
                viewModel.response.value?.options?.get(2)?.option?.let { getHtmlForRender(it) }
                    ?: ""
            val option4 =
                viewModel.response.value?.options?.get(3)?.option?.let { getHtmlForRender(it) }
                    ?: ""

            val plainTextQuestion = Html.fromHtml(question, Html.FROM_HTML_MODE_COMPACT).toString()
            val plainTextOption1 = Html.fromHtml(option1, Html.FROM_HTML_MODE_COMPACT).toString()
            val plainTextOption2 = Html.fromHtml(option2, Html.FROM_HTML_MODE_COMPACT).toString()
            val plainTextOption3 = Html.fromHtml(option3, Html.FROM_HTML_MODE_COMPACT).toString()
            val plainTextOption4 = Html.fromHtml(option4, Html.FROM_HTML_MODE_COMPACT).toString()

            binding.textquestion.text = plainTextQuestion
            binding.textlength11.text = plainTextOption1
            binding.textlength22.text = plainTextOption2
            binding.textlength33.text = plainTextOption3
            binding.textlength44.text = plainTextOption4

        } else {
        }
    }


}


//            binding.textlength22.text = options?.get(1)?.toString()
//            binding.textlength33.text = options?.get(2)?.toString()
//            binding.textlength44.text = options?.get(3)?.toString()