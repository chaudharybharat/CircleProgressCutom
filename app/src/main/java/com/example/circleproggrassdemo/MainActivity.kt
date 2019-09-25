package com.example.circleproggrassdemo

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: ArrayList<ProgressModel> = ArrayList()
        list.add(ProgressModel("100"))
        list.add(ProgressModel("25"))
        list.add(ProgressModel("0"))
        list.add(ProgressModel("0"))

        generatProgressbar(list)


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun generatProgressbar(list: ArrayList<ProgressModel>) {
        var param_linear: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        var param_circleprogress: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            resources.getDimension(R.dimen.dp_40).toInt(),
            resources.getDimension(R.dimen.dp_40).toInt()
        )
        var param_img: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            resources.getDimension(R.dimen.dp_30).toInt(),
            resources.getDimension(R.dimen.dp_30).toInt()
        )
        var param_view: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.dp_50).toInt(),
            resources.getDimension(R.dimen.dp_view).toInt()
        )

        param_img.addRule(RelativeLayout.CENTER_IN_PARENT)


        for (i in 0 until list.size) {
            //mainView
            var relative_layour = RelativeLayout(this)
            relative_layour.layoutParams = param_linear

            var circle_pregrssbar = CircularProgressBar(this)
            circle_pregrssbar.backgroundProgressBarColor =
                resources.getColor(R.color.prograssbar_primary)
            circle_pregrssbar.progressMax = 100f
            circle_pregrssbar.backgroundProgressBarWidth = resources.getDimension(R.dimen.dp_view).toFloat();
            circle_pregrssbar.progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT;
            circle_pregrssbar.progressBarColor = resources.getColor(R.color.green)
            circle_pregrssbar.progressBarWidth = resources.getDimension(R.dimen.dp_4).toFloat()
            circle_pregrssbar.roundBorder = false
            circle_pregrssbar.layoutParams = param_circleprogress;
            if (!list.get(i).progress.equals("100", ignoreCase = true)) {
                circle_pregrssbar.progress = list.get(i).progress.toFloat()
            } else {
                circle_pregrssbar.progress = 0f
            }
            relative_layour.addView(circle_pregrssbar)


            if (!list.get(i).progress.equals("100", ignoreCase = true)) {

                //textview
                var textview = TextView(this)
                textview.layoutParams = param_img;
                textview.typeface = Typeface.SANS_SERIF
                textview.textSize = 12f
                textview.gravity = Gravity.CENTER
                textview.text = "" + (i + 1);
                textview.setTextColor(ContextCompat.getColor(this, R.color.prograssbar_primary));
                relative_layour.addView(textview)
            } else {
                //Imageview
                var image = ImageView(this)
                image.layoutParams = param_img;
                var padding = resources.getDimension(R.dimen.dp_8).toInt()
                image.setPadding(padding, padding, padding, padding)
                image.background = resources.getDrawable(R.drawable.circle_drawble)
                image.setImageDrawable(resources.getDrawable(R.drawable.check))
                relative_layour.addView(image)
            }

            ll_main.addView(relative_layour)
            //this check condition if last not generat view
            if (i < (list.size - 1)) {

                var view = View(this)
                var left_right = resources.getDimension(R.dimen.right_left_view_marign).toInt()
                var progress=list.get(i).progress.toInt()
                //view line progressbar secondary progress over it manage here
                if(!list.get(i).progress.equals("100",ignoreCase = true)){
                    if((progress>(100/4))){
                        param_view.setMargins(0, left_right, left_right, left_right)
                    }else{
                        param_view.setMargins(left_right, left_right, left_right, left_right)
                    }
                }else{
                    param_view.setMargins(0, left_right, left_right, left_right)

               }

                param_view.gravity = Gravity.CENTER_VERTICAL
                view.layoutParams = param_view
                view.setBackgroundColor(resources.getColor(R.color.prograssbar_primary))
                ll_main.addView(view)
            }
            //add View

        }
    }
}
