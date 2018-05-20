package com.example.vuclip.recyclerviewdemo2

import com.example.vuclip.recyclerviewdemo2.model.Clip
import com.example.vuclip.recyclerviewdemo2.model.Container
import java.util.*

/**
 * Created by Banty on 16/05/18.
 */
class DataProvider {
    fun getData(): List<Container> {
        val containerList: MutableList<Container> = ArrayList()

        val spotlight: Container = Container("spotlight",
                Arrays.asList(
                        Clip("123", "Title 1", R.drawable.image),
                        Clip("465", "Title2", R.drawable.image),
                        Clip("789", "Title3", R.drawable.image)
                ))


        val fullBanner: Container = Container("full_banner",
                Arrays.asList(
                        Clip("123", "Full Banner Title 1", R.drawable.image),
                        Clip("465", "Full Banner Title 2", R.drawable.image),
                        Clip("789", "Full Banner Title 3", R.drawable.image)
                ))

        containerList.add(spotlight)
        containerList.add(fullBanner)

        return containerList
    }
}