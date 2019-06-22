package com.example.mypersonaltrainer.programlist

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.mypersonaltrainer.R
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Alexandr Mikhalev on 22.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class EditProgramDialog : DialogFragment(), View.OnClickListener {

    private lateinit var onClickEditDialogButtonsListener: OnClickEditDialogButtonsListener

    private lateinit var titleEditText: TextInputEditText
    private lateinit var timeOfRestEditText: TextInputEditText

    private lateinit var closeView: ImageView
    private lateinit var saveButton: Button

    private lateinit var title: String
    private lateinit var timeOfRest: String

    private val KEY_FOR_TITLE = "KEY_FOR TITLE"
    private val KEY_FOR_TIME_OF_REST = "KEY_FOR_TIME_OF_REST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments!!.getString(KEY_FOR_TITLE)!!
        timeOfRest = arguments!!.getString(KEY_FOR_TIME_OF_REST)!!

        if (parentFragment is OnClickEditDialogButtonsListener) {
            onClickEditDialogButtonsListener = parentFragment as OnClickEditDialogButtonsListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.dialog_edit_program, container, false)
        titleEditText = view.findViewById(R.id.title_edit_text)
        timeOfRestEditText = view.findViewById(R.id.time_of_rest_edit_text)

        closeView = view.findViewById(R.id.close_image_view)
        closeView.setOnClickListener(this)

        saveButton = view.findViewById(R.id.save_button)
        saveButton.setOnClickListener(this)

        titleEditText.setText(title)
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
                onClickEditDialogButtonsListener.onButtonSavedClicked(
                    titleEditText.text.toString(),
                    timeOfRestEditText.text.toString()
                )
                dismiss()
            }
        }
    }

    fun newInstance(
        title: String,
        timeOfRest: String
    ): EditProgramDialog {
        val args = Bundle()
        args.putString(KEY_FOR_TITLE, title)
        args.putString(KEY_FOR_TIME_OF_REST, timeOfRest)
        val fragment = EditProgramDialog()
        fragment.arguments = args
        return fragment
    }
}