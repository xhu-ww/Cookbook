package com.nsx.cookbook.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseFragment
import com.nsx.cookbook.utils.hideSoftKeyBoard
import kotlinx.android.synthetic.main.fragment_food_search.*

class FoodSearchFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.isSubmitButtonEnabled = true //展示搜索完成按钮
        searchView.onActionViewExpanded()       //设置当无内容时、，没有关闭图标
        searchView.postDelayed({
            searchView.isIconified = false      //设置展开
        }, 500)

        searchView.setOnSearchClickListener {
            Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show()
        }
    }

    fun onBack(): Boolean {
        if (searchView.hasFocus()) {
            try {
                //反射获取关闭 按钮
                val method = searchView.javaClass.getDeclaredMethod("onCloseClicked")
                method.isAccessible = true
                method.invoke(searchView)
                activity!!.hideSoftKeyBoard()
                searchView.clearFocus()
            } catch (e: Exception) {
                e.printStackTrace()
                return true
            }
            return false
        }
        return true
    }
}
