package com.poquiz.presentation.ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import com.poquiz.domain.model.Rank
import com.poquiz.presentation.R
import com.ramotion.expandingcollection.ECCardContentListItemAdapter


class CardListItemAdapter(context: Context, objects: List<Rank>) :
    ECCardContentListItemAdapter<Rank?>(context, R.layout.item_ranking, objects) {
    override fun getView(position: Int, @Nullable convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        var rowView = convertView
        if (rowView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.item_ranking, null)
            viewHolder = ViewHolder()
            viewHolder.ranking = rowView!!.findViewById<View>(R.id.tvRank) as TextView?
            viewHolder.nickname = rowView!!.findViewById<View>(R.id.tvNickname) as TextView?
            viewHolder.score= rowView!!.findViewById<View>(R.id.tvScore) as TextView?
            rowView.setTag(viewHolder)
        } else {
            viewHolder = rowView.tag as ViewHolder
        }
        val item = getItem(position)
        if (item != null) {
            viewHolder.ranking!!.text = "${position+1}등"
            viewHolder.nickname!!.text = item.nickname
            viewHolder.score!!.text = "${item.score}점"
        }
        return rowView!!
    }

    internal class ViewHolder {
        var ranking: TextView? = null
        var nickname: TextView? = null
        var score: TextView? = null
    }
}