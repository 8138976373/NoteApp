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
import kotlinx.android.synthetic.main.fragment_add.*
import javax.inject.Inject

class AddFragment :DaggerFragment(){
    lateinit var  noteViewModel:NoteViewModel
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()

        btnAdd.setOnClickListener{
            Navigation.findNavController(requireActivity(),R.id.container).popBackStack()
        }
    }


    override fun onDestroyView() {
        saveNoteToDatabaseFinalCheck()
        (activity as MainActivity).showFloatingButton()
        super.onDestroyView()
    }

    private fun saveNoteToDatabaseFinalCheck(){
        if(validations()){
            Toast.makeText(activity,"note is saved",Toast.LENGTH_SHORT).show()
            saveToDatabase()
        }
        else
        {
            Toast.makeText(activity,"note is discarded",Toast.LENGTH_SHORT).show()
        }

    }
    private fun saveToDatabase(){
        val note=Note(0,addTitle.text.toString(),addDescription.text.toString(),addTag.text.toString())
        if(addTitle.text.isNullOrEmpty()){
            note.title="Empty Title"
            noteViewModel.insert(note)
        }
        else
        {
            noteViewModel.insert(note)
        }
    }

    private fun setUpViewModel(){
        noteViewModel=ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)

    }
    private fun validations():Boolean{
        if(addTitle.text.isNullOrEmpty()&& addDescription.text.isNullOrEmpty()&& addTag.text.isNullOrEmpty()) {
            return false
        }
        else
        {
            return true
        }

    }
}