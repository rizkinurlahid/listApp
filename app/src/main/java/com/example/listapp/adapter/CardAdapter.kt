import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.activity.DetailActivity
import com.example.listapp.holder.Holder
import com.example.listapp.R
import com.example.listapp.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class CardAdapter(private val dataList: MutableList<Article>) : RecyclerView.Adapter<Holder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = dataList[position]

        val imageView = holder.itemView.item_img
        val titleTextView = holder.itemView.item_tv_title
        val descriptionTextView = holder.itemView.item_tv_description
        val publishedTextView = holder.itemView.item_tv_published

        titleTextView.text = data.title
        descriptionTextView.text = data.description
        publishedTextView.text = data.publishedAt

        Picasso.get()
            .load(data.urlToImage)
            .into(imageView)

        holder.itemView.setOnClickListener {
            val moveIntentWithData = Intent(it.context, DetailActivity::class.java)
            moveIntentWithData.putExtra(DetailActivity.imgKey, data.urlToImage)
            moveIntentWithData.putExtra(DetailActivity.authorKey, data.author)
            moveIntentWithData.putExtra(DetailActivity.publishedKey, data.publishedAt)
            moveIntentWithData.putExtra(DetailActivity.titleKey, data.title)
            moveIntentWithData.putExtra(DetailActivity.contentKey, data.content)
            moveIntentWithData.putExtra(DetailActivity.urlKey, data.url)
            it.context.startActivity(moveIntentWithData)
        }
    }
}