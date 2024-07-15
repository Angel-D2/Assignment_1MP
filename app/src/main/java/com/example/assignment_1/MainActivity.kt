package com.example.assignment_1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var selectedCategory: String
    private lateinit var entryEditText: EditText
    private lateinit var entriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Spinner
        val spinner: Spinner = findViewById(R.id.categorySpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Set Spinner onItemSelectedListener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCategory = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Initialize the Button, EditText, and TextView
        val button: Button = findViewById(R.id.addButton)
        entryEditText = findViewById(R.id.entryEditText)
        entriesTextView = findViewById(R.id.entriesTextView)

        // Set Button onClickListener
        button.setOnClickListener {
            addJournalEntry()
        }
    }

    private fun addJournalEntry() {
        val entry = entryEditText.text.toString()
        if (entry.isNotEmpty()) {
            val currentText = entriesTextView.text.toString()
            val newText = if (currentText.isEmpty()) {
                "$selectedCategory: $entry"
            } else {
                "$currentText\n$selectedCategory: $entry"
            }
            entriesTextView.text = newText
            entryEditText.text.clear()
        } else {
            Toast.makeText(this, "Please write a journal entry", Toast.LENGTH_SHORT).show()
        }
    }
}
