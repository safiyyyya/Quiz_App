import android.app.Fragment
import android.app.FragmentManager
import androidx.fragment.app.supportFragment
import androidx.fragment.app.FragmentManager
import java.util.*

 open class PagerAdapter(supportFragmentManager: FragmentManager) :
     FragmentStatePagerAdapter(supportFragmentManager) {

    // declare arrayList to contain fragments and its title
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    fun getItem(position: Int): Fragment {
        // return a particular fragment page
        return mFragmentList[position]
    }

   fun getCount(): Int {
        // return the number of tabs
        return mFragmentList.size
    }

  fun getPageTitle(position: Int): CharSequence{
        // return title of the tab
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        // add each fragment and its title to the array list
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}


