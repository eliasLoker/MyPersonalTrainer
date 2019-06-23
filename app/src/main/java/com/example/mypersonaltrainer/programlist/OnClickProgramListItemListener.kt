package com.example.mypersonaltrainer.programlist

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface OnClickProgramListItemListener {

    fun onClickStartButton(id: Long)

    fun onSettingsClicked(id: Long)

    fun onBasketClicked(id: Long)
}