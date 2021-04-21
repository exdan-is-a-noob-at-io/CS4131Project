package com.example.cs4131projecteddenchew.ui.answer_question

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.Comment
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.question_suggest.RecyclerAdapterCreatedQuestionList
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import kotlinx.android.synthetic.main.fragment_view_comments.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewCommentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewCommentsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerViewComments: RecyclerView
    lateinit var layoutManagerComments: LinearLayoutManager


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewComments = view.findViewById(R.id.recycler_view_comments)
        layoutManagerComments = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewComments.setLayoutManager(layoutManagerComments)

        var adapterComments = context?.let { RecyclerAdapterCommentsSection(it) }
        recyclerViewComments.setAdapter(adapterComments)

        floating_action_button.setOnClickListener{
            // Initialize a new layout inflater instance
            val inflater:LayoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.make_comment_pop_up,null)

            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
            )

            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true)

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }


            // If API level 23 or higher then execute the code
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }

            // Get the widgets reference from custom view
            val editText = view.findViewById<EditText>(R.id.editTextComment)
            val button = view.findViewById<Button>(R.id.postButton)

            // Set click listener for popup window's text view
            button.setOnClickListener {
                if (!editText.text.toString().equals("")) FirebaseUtil.getCommentID()
            }

            val resultObserver = Observer<Long>{
                    result ->
                val minusOne:Long = -1
                if (result != minusOne){
                    Log.i("TAG", "comment observer called")
                    var comment = Comment(result, editText.text.toString(), User.decryptVal(adminViewModel.user_data.value?.name), Date())

                    if (comment.comment.equals("")) return@Observer
                    Log.i("TAG", comment.toString())
                    FirebaseUtil.addComment(comment, RoundOneAnswerQuestionViewModel.previousPost, requireContext())
                    adapterComments?.addObject(comment)
                    adapterComments?.notifyDataSetChanged()
                    Log.i("TAG", "Function Closing")
                    popupWindow.dismiss()
                }


            }
            RoundOneAnswerQuestionViewModel.commentsID.observe(viewLifecycleOwner, resultObserver)



            // Set a dismiss listener for popup window
            popupWindow.setOnDismissListener {
                editText.setText("")
            }


            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(commentsLayout)
            popupWindow.showAtLocation(
                commentsLayout, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
            )
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewCommentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewCommentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}