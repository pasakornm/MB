package com.example.projectandroid

import android.content.Intent

interface CancelOnClickListener {
    fun onClickRemove(reserve: Reserve)
    fun onClickEdit(reserve: Reserve)
}

