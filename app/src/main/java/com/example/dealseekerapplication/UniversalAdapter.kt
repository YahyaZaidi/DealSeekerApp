package com.example.dealseekerapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UniversalAdapter(private var items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Define view types
    companion object {
        private const val TYPE_CAMERA_A = 0
        private const val TYPE_CAMERA = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CameraA -> TYPE_CAMERA_A
            is Camera -> TYPE_CAMERA
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    // ViewHolder for CameraA
    class CameraAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cameraA: CameraA) {
            // Bind CameraA data to the itemView here
            // Example: itemView.textViewName.text = cameraA.name
        }
    }

    // ViewHolder for Camera
    class CameraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(camera: Camera) {
            // Bind Camera data to the itemView here
            // Example: itemView.textViewName.text = camera.productName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CAMERA_A -> CameraAViewHolder(inflater.inflate(R.layout.item_camera_a, parent, false))
            TYPE_CAMERA -> CameraViewHolder(inflater.inflate(R.layout.item_camera, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CameraAViewHolder -> if (items[position] is CameraA) {
                holder.bind(items[position] as CameraA)
            }
            is CameraViewHolder -> if (items[position] is Camera) {
                holder.bind(items[position] as Camera)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    // Update the list of items and notify adapter
    fun updateList(newItems: List<Any>) {
        items = newItems
        notifyDataSetChanged()
    }
}
