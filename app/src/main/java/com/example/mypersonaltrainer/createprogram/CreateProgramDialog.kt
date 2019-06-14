package com.example.mypersonaltrainer.createprogram

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.mypersonaltrainer.R
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramDialog : DialogFragment(), View.OnClickListener {

    private lateinit var listener: OnCreateProgramDialogListener
    private lateinit var saveButton: Button
    private lateinit var titleEditText: TextInputEditText
    private lateinit var timeOfRestEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (parentFragment is OnCreateProgramDialogListener) {
            listener = parentFragment as OnCreateProgramDialogListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.dialog_create_program, container, false)
        saveButton = view.findViewById(R.id.save_button)
        saveButton.setOnClickListener(this)

        titleEditText = view.findViewById(R.id.title_edit_text)
        timeOfRestEditText = view.findViewById(R.id.time_of_rest_edit_text)
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
            R.id.save_button -> {
                listener.onClickSaveButton(titleEditText.text.toString(), timeOfRestEditText.text.toString())
            }
        }
    }

    fun newInstance(): CreateProgramDialog {
        val args = Bundle()
        val fragment = CreateProgramDialog()
        fragment.arguments = args
        return fragment
    }
}