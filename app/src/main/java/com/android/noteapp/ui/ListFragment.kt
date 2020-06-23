package com.android.noteapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.noteapp.R
import com.android.noteapp.persistence.Note
import com.android.noteapp.ui.adapter.NoteAdapter
import com.android.noteapp.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment :DaggerFragment(),NoteAdapter.Interaction {

    override fun onItemSelected(position: Int, item: Note) {
        val navDirection=ListFragmentDirections.actionListFragmentToEditFragment(item)
        findNavController().navigate(navDirection)
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var noteViewModel: NoteViewModel

    lateinit var noteAdapter: NoteAdapter

    lateinit var allNotes:List<Note>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allNotes= mutableListOf()
        return inflater.inflate(R.layout.fragment_list,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        initRecyclerView()
        observeLiveData()

    }

    private fun initRecyclerView(){
        noteAdapter=NoteAdapter(allNotes,this@ListFragment)
        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@ListFragment.context)
            adapter=noteAdapter
            val swipe=ItemTouchHelper(initSwipeToDelete())
            swipe.attachToRecyclerView(recyclerView)
        }


    }
    private fun observeLiveData(){
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            allNotes=it
            noteAdapter.swap(it)
        })

    }
    private fun setupViewModel(){
        noteViewModel=ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)

    }


    private fun initSwipeToDelete():ItemTouchHelper.SimpleCallback{
        return object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position=viewHolder.adapterPosition
                val note=allNotes.get(position)
                noteViewModel.delete(note)
                Toast.makeText(activity,"Note deleted",Toast.LENGTH_SHORT).show()
            }

        }
    }
}