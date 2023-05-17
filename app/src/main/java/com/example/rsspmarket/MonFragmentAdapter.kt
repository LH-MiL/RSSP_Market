import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MonFragmentAdapter(var FM: FragmentManager): FragmentPagerAdapter(FM){
    var listFragment = arrayListOf<Fragment>()
    var listTitle= arrayListOf<String>()
    override fun getCount():Int{ return listFragment.size   }
    override fun getItem(position: Int): Fragment { return listFragment[position] }
    override fun getPageTitle(position: Int): String { return listTitle[position] }
    fun addFragment(fragment:Fragment,title:String){
        listFragment.add(fragment)
        listTitle.add(title)   }      }
