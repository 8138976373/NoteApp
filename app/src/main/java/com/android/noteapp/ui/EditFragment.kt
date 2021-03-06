package com.android.noteapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.noteapp.R
import com.android.noteapp.persistence.Note
import com.android.noteapp.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_edit.*
import javax.inject.Inject

class EditFragment :DaggerFragment() {

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareNoteForEditing()
        setupViewModel()

        btnEdit.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.container).popBackStack()
        }
    }


    private fun saveNoteToDatabase() {

        if (validations()) {
            Toast.makeText(activity, "Note is saved", Toast.LENGTH_SHORT).show()
            saveNote()
            val id:Int = EditFragmentArgs.fromBundle(arguments!!).note?.id!!


        } else {
            Toast.makeText(activity, "Note is Discarded", Toast.LENGTH_SHORT).show()
            val id: Int = EditFragmentArgs.fromBundle(arguments!!).note?.id!!
            noteViewModel.deleteById(id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNoteToDatabase()
        (activity as MainActivity?)?.showFloatingButton()
    }

    private fun saveNote() {

        val id:Int? = EditFragmentArgs.fromBundle(arguments!!).note?.id

        val note = Note(id!!,editTitle.text.toString(),editDescription.text.toString(),editTag.text.toString())

        if (editTitle.text.isNullOrEmpty()) {
            note.title = "Empty Title"
            noteViewModel.update(note)

        }else{
                       noteViewModel.update(note)
        }
    }

    fun validations(): Boolean {
        return !(editTitle.text.isNullOrEmpty() && editDescription.text.isNullOrEmpty() && editTag.text.isNullOrEmpty())
    }


    private fun setupViewModel() {
        noteViewModel = ViewModelProvider(this,viewmodelProviderFactory).get(NoteViewModel::class.java)
    }

    private fun prepareNoteForEditing() {
               arguments?.let {
            val safeArgs = EditFragmentArgs.fromBundle(it)
            val note = safeArgs.note
            editTitle.setText(note?.title.toString())
            editDescription.setText(note?.description.toString())
            editTag.setText(note?.tag.toString())
        }

    }
}

