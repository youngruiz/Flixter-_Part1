package com.codepath.bestsellerlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.bestsellerlistapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject
import android.content.Context

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class BestSellerBooksFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_best_seller_books_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY


        // Using the client, perform the HTTP request
        client[
                //"https://api.themoviedb.org/3/movie/now_playing",
                "https://api.themoviedb.org/3/trending/tv/week",
                params,
                object : JsonHttpResponseHandler() {

                    override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                        Log.i("Daniel", "err")
                        Log.i("Daniel", "Statuscode: + $statusCode + \n + Errorcode: + $response")
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                        // The wait for a response is over
                        progressBar.hide()

                        // This is called when response HTTP status is "200 OK"
                        Log.i("Daniel", "Everything went well.")

                        if (json == null){
                            Log.i("Daniel", "Oh no")
                        }

                        Log.i("Daniel", json.toString())

                        val resultsJSON: JSONObject = json?.jsonObject as JSONObject

                        val booksRawJSON : String = resultsJSON.get("results").toString()

                        Log.i("Daniel", resultsJSON.toString())
                        Log.i("Daniel", booksRawJSON)

                        // Create gson object.
                        val gson = Gson()
                        val arrayBookType = object : TypeToken<List<BestSellerBook>>() {}.type
                        val models : List<BestSellerBook> = gson.fromJson(booksRawJSON, arrayBookType)
                        recyclerView.adapter =
                            context?.let { BestSellerBooksRecyclerViewAdapter(it, models, this@BestSellerBooksFragment) }

                    }
                }


        ]

        /* Uncomment me once you complete the above sections!
        {
            /*
             * The onSuccess function gets called when
             * HTTP response status is "200 OK"
             */
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                // The wait for a response is over
                progressBar.hide()

                //TODO - Parse JSON into Models

                val models : List<BestSellerBook> = null // Fix me!
                recyclerView.adapter = BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)

                // Look for this in Logcat:
                Log.d("BestSellerBooksFragment", "response successful")
            }

            /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                // The wait for a response is over
                progressBar.hide()

                // If the error is not null, log it!
                t?.message?.let {
                    Log.e("BestSellerBooksFragment", errorResponse)
                }
            }
        }]
        */

    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: BestSellerBook) {
        Toast.makeText(context, "test: " + item.original_title, Toast.LENGTH_LONG).show()
    }

}
