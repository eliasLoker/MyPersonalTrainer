package com.example.mypersonaltrainer.programlist

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.mypersonaltrainer.R

/**
 * Created by Alexandr Mikhalev on 23.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class DeleteProgramDialog: DialogFragment(), View.OnClickListener {

    private lateinit var confirmTextView: TextView
    private lateinit var cancelTextView: TextView
    private lateinit var deleteTextView: TextView

    private lateinit var onClickDeleteDialogButtonsListener: OnClickDeleteDialogButtonsListener

    private var number: String? = ""
    private val KEY: String = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            this.number = it.getString(KEY)
        }

        if (parentFragment is OnClickDeleteDialogButtonsListener) {
            onClickDeleteDialogButtonsListener = parentFragment as OnClickDeleteDialogButtonsListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view: View = inflater.inflate(R.layout.dialog_delete_exercise, container, false)
        confirmTextView = view.findViewById(R.id.confirm_button)
        cancelTextView = view.findViewById(R.id.cancel_button)
        deleteTextView = view.findViewById(R.id.delete_text_view)

        val text = String.format(resources.getString(R.string.delete_program_message), number)
        deleteTextView.text = text

        confirmTextView.setOnClickListener(this)
        cancelTextView.setOnClickListener(this)
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
        if (p0!!.id == R.id.confirm_button) {
            onClickDeleteDialogButtonsListener.onConfirmDeleteDialogClicked()
            dialog!!.dismiss()
        } else if (p0.id == R.id.cancel_button) {
            dismiss()
        }
    }

    fun newInstance(message: String?): DeleteProgramDialog {
        val args = Bundle()
        args.putString(KEY, message)
        val fragment = DeleteProgramDialog()
        fragment.arguments = args
        return fragment
    }
}