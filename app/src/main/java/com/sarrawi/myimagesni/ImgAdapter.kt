package com.sarrawi.myimagesni

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sarrawi.myimagesni.R
import com.sarrawi.myimagesni.databinding.ImgDesignBinding

class ImgAdapter(val con: Context): RecyclerView.Adapter<ImgAdapter.ViewHolder>() {
    var onItemClick: ((Int,ImgsModel, Int) -> Unit)? = null

    private var isInternetConnected: Boolean = true

    var onbtnClick: ((item:ImgsModel,position:Int) -> Unit)? = null
    val displayMetrics = con.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    // قم بتحديد القيم المطلوبة للصورة
    val targetWidth = screenWidth / 2 // على سبيل المثال، يمكنك تحديد العرض إلى نصف عرض الشاشة
    val targetHeight = screenHeight / 2 // على سبيل المثال، يمكنك تحديد الارتفاع إلى نصف ارتفاع الشاشة

    inner class ViewHolder(val binding: ImgDesignBinding):RecyclerView.ViewHolder(binding.root) {

        init {

                binding.root.setOnClickListener {
                    //اذا كانت null سيتم استخدام 0؟
//                    onItemClick?.invoke(img_list[layoutPosition].id ?: 0,img_list[layoutPosition].image_url, layoutPosition ?: 0)
                    onItemClick?.invoke(img_list[layoutPosition].id ?: 0, img_list[layoutPosition], layoutPosition)

                }

                binding.imgFave.setOnClickListener {
                    onbtnClick?.invoke(img_list[position], position)
                }



            }



        fun bind(position: Int) {


                val current_imgModel = img_list[position]
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_baseline_autorenew_24)
                    .error(R.drawable.error_a)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)

                Glide.with(con)
                    .asBitmap() // تحميل الصورة كـ Bitmap
                    .load(current_imgModel.image_url)
                    .apply(requestOptions)
                    .override(targetWidth, targetHeight)
                    .circleCrop()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgadapterImgViewContent)



                binding.apply {
                    if(current_imgModel.is_fav){
                        imgFave.setImageResource(R.drawable.baseline_favorite_true)
                    }else{
                        imgFave.setImageResource(R.drawable.baseline_favorite_border_false)
                    }

                }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ImgsModel>(){
        override fun areItemsTheSame(oldItem: ImgsModel, newItem: ImgsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImgsModel, newItem: ImgsModel): Boolean {
            return newItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)
    var img_list: List<ImgsModel>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ImgDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return img_list.size
    }
//a
    fun updateInternetStatus(isConnected: Boolean) {
        isInternetConnected = isConnected
        notifyDataSetChanged()
    }

    fun updateData(newData: List<ImgsModel>) {
        img_list = newData
        notifyDataSetChanged()
    }

}