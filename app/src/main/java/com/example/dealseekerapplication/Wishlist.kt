package com.example.dealseekerapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.json.JSONArray

class Wishlist : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: WishlistAdapter
    private var wishlistItems = ArrayList<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadWishlistItems()
        adapter.notifyDataSetChanged()
        updateUI()  // Call a method to handle UI updates
    }

    private fun updateUI() {
        val emptyStateTextView = view?.findViewById<TextView>(R.id.empty_state_textview)
        if (wishlistItems.isEmpty()) {
            listView.visibility = View.GONE
            emptyStateTextView?.visibility = View.VISIBLE
        } else {
            listView.visibility = View.VISIBLE
            emptyStateTextView?.visibility = View.GONE
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wishlist, container, false)
        listView = view.findViewById(R.id.wishlist_list_view)
        adapter = WishlistAdapter(requireContext(), wishlistItems)
        listView.adapter = adapter
        return view
    }


    private fun loadWishlistItems() {
        val prefs = requireActivity().getSharedPreferences("WishlistPrefs", Context.MODE_PRIVATE)
        val itemsJson = prefs.getString("wishlistItems", null)
        Log.d("Wishlist", "Loading items: $itemsJson")
        itemsJson?.let {
            val jsonArray = JSONArray(it)
            wishlistItems.clear()
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getString(i)
                wishlistItems.add(item)
                Log.d("Wishlist", "Loaded item: $item")
            }
        }
        if (wishlistItems.isEmpty()) {
            Log.d("Wishlist", "No items found in SharedPreferences.")
        }
    }

    private fun saveWishlistItems() {
        val prefs = requireActivity().getSharedPreferences("WishlistPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val jsonArray = JSONArray()
        wishlistItems.forEach { jsonArray.put(it) }
        editor.putString("wishlistItems", jsonArray.toString())
        editor.apply()
    }

    inner class WishlistAdapter(context: Context, objects: ArrayList<String>)
        : ArrayAdapter<String>(context, R.layout.wishlist_item, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = LayoutInflater.from(context)
            val view = convertView ?: inflater.inflate(R.layout.wishlist_item, parent, false)

            val itemName = view.findViewById<TextView>(R.id.item_name)
            val removeButton = view.findViewById<Button>(R.id.remove_button)

            val item = getItem(position)
            itemName.text = item ?: "Missing Item"

            removeButton.setOnClickListener {
                remove(item)
                notifyDataSetChanged()
                saveWishlistItems()
                Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
            }

            return view
        }
    }



}



