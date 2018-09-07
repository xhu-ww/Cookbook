package com.nsx.cookbook.ui.food

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.nsx.cookbook.BuildConfig
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseActivity
import com.nsx.cookbook.utils.addFragment

/**
 * @param showSearch 是否展示搜索界面 默认展示
 * @param category 食谱分类 默认参数为空
 */
fun FoodActivityIntent(showSearch: Boolean = true, category: String? = null): Intent =
    Intent().apply {
        setClassName(BuildConfig.APPLICATION_ID, FoodActivity::class.java.name)
        putExtra(EXTRA_SHOW_SEARCH, showSearch)
        if (category != null) putExtra(EXTRA_FOOD_CATEGORY, category)
    }

const val EXTRA_SHOW_SEARCH = "show_search"
const val EXTRA_FOOD_CATEGORY = "food_category"

class FoodActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        setSupportActionBar(findViewById(R.id.toolbar))

        setTitle(R.string.food_search)
        supportFragmentManager.addFragment(FoodSearchFragment(), R.id.rootFrameLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.rootFrameLayout)
        if (fragment is FoodSearchFragment) {
            if (fragment.onBack()) super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }
}
