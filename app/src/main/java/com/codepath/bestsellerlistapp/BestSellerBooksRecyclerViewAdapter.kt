package com.codepath.bestsellerlistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bestsellerlistapp.R.id

import com.bumptech.glide.Glide

import android.util.Log



/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class BestSellerBooksRecyclerViewAdapter(
    private val context: Context,
    private val books: List<BestSellerBook>,
    private val mListener: OnListFragmentInteractionListener?,
    )
    : RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestSellerBook? = null
        //val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        // val mBookDescription: TextView = mView.findViewById<View>(id.book_description) as TextView
        val mBookImage: ImageView = mView.findViewById<View>(id.book_image) as ImageView

//        override fun toString(): String {
//            return "https://image.tmdb.org/t/p/w500/" + mBookImage.toString()
//        }
//
//        override fun toString(): String {
//            return mBookTitle.toString() + " '" + mBookAuthor.text + "'"
//        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        //holder.mBookTitle.text = book.original_title
        // holder.mBookDescription.text = book.overview

        var fullUrl = "https://image.tmdb.org/t/p/w500/" + book.poster_path

        Glide.with(holder.mView)
            .load(fullUrl)
            .centerInside()
            .into(holder.mBookImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return books.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

//        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
//        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
//        private val abstractTextView = itemView.findViewById<TextView>(R.id.mediaAbstract)
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        // TODO: Write a helper method to help set up the onBindViewHolder method
//        fun bind(article: Article) {
//            titleTextView.text = article.headline?.main
//            abstractTextView.text = article.abstract
//
//            Glide.with(context)
//                .load(article.mediaImageUrl)
//                .into(mediaImageView)
//        }

        override fun onClick(v: View?) {
//            // TODO: Get selected article
//            var article = articles[absoluteAdapterPosition]
//
//            // TODO: Navigate to Details screen and pass selected article
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra(ARTICLE_EXTRA, article)
//            context.startActivity(intent)
            val book = books[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }


}