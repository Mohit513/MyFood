package com.example.myfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfood.adapter.Food_RV_Adapter
import com.example.myfood.databinding.FragmentFoodBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentFoodBinding
    private lateinit var adapter: Food_RV_Adapter
    private val items:MutableList<Item> = mutableListOf(
        Item("Pizza", "A classic Italian dish made with melted cheese and tomato sauce", "New York", "Rating 4.8", R.drawable.pizza),
        Item("Sushi", "A traditional Japanese dish made with vinegared rice and raw fish", "Tokyo", "Rating 4.9", R.drawable.sushi),
        Item("Tacos", "A Mexican dish made with a crispy shell, meat, and vegetables", "Mexico City", "Rating 4.7", R.drawable.tacos),
        Item("Burgers", "A classic American sandwich made with a beef patty and toppings", "Los Angeles", "Rating 4.6", R.drawable.burger),
        Item("Curry", "A popular Indian dish made with a spicy sauce and meat or vegetables", "Mumbai", "Rating 4.8", R.drawable.curry),
        Item("Ramen", "A Japanese noodle soup made with a rich broth and vegetables", "Osaka", "Rating 4.9", R.drawable.ramen),
        Item("Falafel", "A Middle Eastern dish made with crispy chickpea patties and tahini sauce", "Tel Aviv", "Rating 4.7", R.drawable.hummus),
        Item("Steak", "A classic American dish made with a grilled steak and vegetables", "Chicago", "Rating 4.8", R.drawable.steak),
        Item("Fish and Chips", "A British dish made with battered and fried fish and crispy fries", "London", "Rating 4.7", R.drawable.sandwich),
        Item("Pad Thai", "A Thai dish made with stir-fried noodles, vegetables, and shrimp", "Bangkok", "Rating 4.8", R.drawable.padthai),
        Item("Chow Mein", "A Chinese dish made with stir-fried noodles, vegetables, and meat", "Beijing", "Rating 4.7", R.drawable.chowmein),
        Item("Gyro", "A Greek dish made with a crispy pita, meat, and vegetables", "Athens", "Rating 4.8", R.drawable.gyro),
        Item("Quesadillas", "A Mexican dish made with a crispy tortilla, cheese, and vegetables", "Guadalajara", "Rating 4.7", R.drawable.quesadilla),
        Item("Dumplings", "A Chinese dish made with steamed or pan-fried dumplings and dipping sauce", "Shanghai", "Rating 4.8", R.drawable.dumpling),
        Item("Shawarma", "A Middle Eastern dish made with a crispy pita, meat, and vegetables", "Cairo", "Rating 4.7", R.drawable.swarma)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater, container, false)

        recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Food_RV_Adapter(items)
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}