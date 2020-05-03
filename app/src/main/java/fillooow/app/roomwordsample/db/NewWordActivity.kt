package fillooow.app.roomwordsample.db

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import fillooow.app.roomwordsample.R
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener { onBtnSaveClick() }
    }

    private fun onBtnSaveClick() {

        val replyIntent = Intent()
        when (TextUtils.isEmpty(edit_word.text)) {

            true -> setResult(Activity.RESULT_CANCELED, replyIntent)
            false -> {
                val word = edit_word.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
        }
        finish()
    }
}