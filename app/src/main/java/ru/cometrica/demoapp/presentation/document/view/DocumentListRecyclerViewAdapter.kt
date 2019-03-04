package ru.cometrica.demoapp.presentation.document.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import ru.cometrica.demoapp.R
import ru.cometrica.demoapp.presentation.document.model.DocumentViewModel

class DocumentListRecyclerViewAdapter : RecyclerView.Adapter<DocumentListRecyclerViewAdapter.ViewHolder>() {

    var data: List<DocumentViewModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var disposable: Disposable? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.author
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mIdView: TextView = view.textView1
        val mContentView: TextView = view.textView2

        override fun toString(): String =
            super.toString() + " '" + mContentView.text + "'"
    }

    fun bind(source: Observable<List<DocumentViewModel>>) {
        if (disposable?.isDisposed != true) disposable?.dispose()
        disposable = source.subscribe { data = it }
    }

}
