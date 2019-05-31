import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.extension.toDate
import com.vitantonio.nagauzzi.unusedappfinder.extension.toInstalledDateString
import com.vitantonio.nagauzzi.unusedappfinder.extension.toLastUsedDateString
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

class GridAdapter(
    private val context: Context,
    private val layoutId: Int,
    private val appItemList: List<AppUsage>
) : BaseAdapter() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    internal inner class ViewHolder(
        val imageView: ImageView,
        val textViewAppName: TextView,
        val textViewAppLastUsedTime: TextView,
        val textViewAppInstalledTime: TextView
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val holder: ViewHolder
        if (view == null) {
            view = inflater.inflate(layoutId, parent, false)
            holder = ViewHolder(
                imageView = view.findViewById(R.id.image_view),
                textViewAppName = view.findViewById(R.id.text_view_app_name),
                textViewAppLastUsedTime = view.findViewById(R.id.text_view_app_last_used_time),
                textViewAppInstalledTime = view.findViewById(R.id.text_view_app_installed_time)
            )
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        holder.imageView.setImageDrawable(appItemList[position].icon)
        holder.textViewAppName.text = appItemList[position].name
        holder.textViewAppLastUsedTime.text =
            appItemList[position].lastUsedTime.toDate().toLastUsedDateString(context)
        holder.textViewAppInstalledTime.text =
            appItemList[position].installedTime.toDate().toInstalledDateString(context)

        return view!!
    }

    override fun getCount(): Int {
        return appItemList.size
    }

    override fun getItem(position: Int): Any? {
        return appItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
