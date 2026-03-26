package com.example.iqtest.data.local

import android.content.Context
import com.example.iqtest.domain.model.Question
import org.json.JSONArray

class QuestionsLocalDataSource(
    private val context: Context
) {
    fun loadQuestions(): List<Question> {
        val rawJson = context.assets.open("questions.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(rawJson)
        return buildList {
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val optionsJson = item.getJSONArray("options")
                val options = buildList {
                    for (j in 0 until optionsJson.length()) {
                        add(optionsJson.getString(j))
                    }
                }
                add(
                    Question(
                        id = item.getInt("id"),
                        text = item.getString("text"),
                        options = options,
                        correctOptionIndex = item.getInt("correctOptionIndex")
                    )
                )
            }
        }
    }
}
