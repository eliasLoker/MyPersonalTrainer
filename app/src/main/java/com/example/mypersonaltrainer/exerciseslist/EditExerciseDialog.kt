package com.example.mypersonaltrainer.exerciseslist

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.mypersonaltrainer.R
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Alexandr Mikhalev on 12.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class EditExerciseDialog : DialogFragment(), View.OnClickListener {

    private lateinit var onClickSettingsDialogButtonsListener: OnClickSettingsDialogButtonsListener

    private lateinit var titleEditText: TextInputEditText
    private lateinit var numberOfRepeatEditText: TextInputEditText
    private lateinit var repetitionsEditText: TextInputEditText
    private lateinit var timeOfRestEditText: TextInputEditText

    private lateinit var closeView: ImageView
    private lateinit var saveButton: Button

    private val KEY_FOR_TITLE = "KEY_FOR TITLE"
    private val KEY_FOR_NUMBER_OF_REPEAT = "KEY_FOR_NUMBER_OF_REPEAT"
    private val KEY_FOR_NUMBER_OF_REPETITIONS = "KEY_FOR_NUMBER_OF_REPETITIONS"
    private val KEY_FOR_TIME_OF_REST = "KEY_FOR_TIME_OF_REST"

    private lateinit var title: String
    private lateinit var numberOfRepeat: String
    private lateinit var numberOfRepetitions: String
    private lateinit var timeOfRest: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments!!.getString(KEY_FOR_TITLE)!!
        numberOfRepeat = arguments!!.getString(KEY_FOR_NUMBER_OF_REPEAT)!!
        numberOfRepetitions = arguments!!.getString(KEY_FOR_NUMBER_OF_REPETITIONS)!!
        timeOfRest = arguments!!.getString(KEY_FOR_TIME_OF_REST)!!

        if (parentFragment is OnClickSettingsDialogButtonsListener) {
            onClickSettingsDialogButtonsListener = parentFragment as OnClickSettingsDialogButtonsListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.dialog_edit_exercise, container, false)

        titleEditText = view.findViewById(R.id.title_edit_text)
        numberOfRepeatEditText = view.findViewById(R.id.amount_edit_text)
        repetitionsEditText = view.findViewById(R.id.repetitions_edit_text)
        timeOfRestEditText = view.findViewById(R.id.time_of_rest_edit_text)

        closeView = view.findViewById(R.id.close_image_view)
        closeView.setOnClickListener(this)

        saveButton = view.findViewById(R.id.save_button)
        saveButton.setOnClickListener(this)

        titleEditText.setText(title)
        numberOfRepeatEditText.setText(numberOfRepeat)
        repetitionsEditText.setText(numberOfRepetitions)
        timeOfRestEditText.setText(timeOfRest)
        return view
    }

    override fun onResume() {
        super.onResume()
        setWindow()
    }

    private fun setWindow() {
        dialog!!.setCanceledOnTouchOutside(false)
        val width = WindowManager.LayoutParams.MATCH_PARENT
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        val window = dialog!!.window
        window!!.setLayout(width, height)
        window.setGravity(Gravity.CENTER)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.close_image_view -> dismiss()
            R.id.save_button -> {
                onClickSettingsDialogButtonsListener.onButtonSavedClicked(
                    titleEditText.text.toString(),
                    numberOfRepeatEditText.text.toString(),
                    repetitionsEditText.text.toString(),
                    timeOfRestEditText.text.toString()
                )
                dismiss()
            }
        }
    }

    fun newIntstance(
        title: String,
        numberOfRepeat: String,
        numberOfRepetitions: String,
        timeOfRest: String
    ): EditExerciseDialog {
        val args = Bundle()
        args.putString(KEY_FOR_TITLE, title)
        args.putString(KEY_FOR_NUMBER_OF_REPEAT, numberOfRepeat)
        args.putString(KEY_FOR_NUMBER_OF_REPETITIONS, numberOfRepetitions)
        args.putString(KEY_FOR_TIME_OF_REST, timeOfRest)
        val fragment = EditExerciseDialog()
        fragment.arguments = args
        return fragment
    }
}