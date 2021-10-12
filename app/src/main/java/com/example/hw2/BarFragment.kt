package com.example.hw2

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import kotlinx.android.synthetic.main.fragment_bar.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*
import com.androidplot.xy.BarRenderer
import android.widget.ArrayAdapter







// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BarFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val domainLabels = arrayOf<Number>(1, 2, 3, 4);
        val series1Number = arrayOf<Number>(4.3, 2.5, 3.5, 4.5);
        val series2Number = arrayOf<Number>(2.4, 4.4, 1.8, 2.8);
        val series3Number = arrayOf<Number>(2, 2, 3, 5);

        val series1: XYSeries
        val series2: XYSeries
        val series3: XYSeries

        series1 = SimpleXYSeries(
            Arrays.asList(* series1Number),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "a"
        );
        series2 = SimpleXYSeries(
            Arrays.asList(* series2Number),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "b"
        );
        series3 = SimpleXYSeries(
            Arrays.asList(* series3Number),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "c"
        );
        PixelUtils.init(this.context)
        val series1Format = BarFormatter(Color.rgb(77,115, 190), Color.rgb(77,115, 190))
        val series2Format = BarFormatter(Color.rgb(223,129, 67), Color.rgb(223,129, 67))
        val series3Format = BarFormatter(Color.rgb(165,165, 165), Color.rgb(165,165, 165))

        series1Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                10,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        series2Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                10,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        series3Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                10,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        graphBar.setDomainStep(StepMode.INCREMENT_BY_VAL, 1.0)
        graphBar.setRangeStep(StepMode.INCREMENT_BY_VAL, 1.0)
        graphBar.setRangeBoundaries(0, 6, BoundaryMode.FIXED)
        graphBar.addSeries(series1, series1Format)
        graphBar.addSeries(series2, series2Format)
        graphBar.addSeries(series3, series3Format)
        graphBar.linesPerRangeLabel = 1
        graphBar.linesPerDomainLabel = 1
        val renderer: BarRenderer<*> = graphBar.getRenderer(BarRenderer::class.java)
        renderer.barOrientation = BarRenderer.BarOrientation.SIDE_BY_SIDE
        renderer.setBarGroupWidth(BarRenderer.BarGroupWidthMode.FIXED_WIDTH, PixelUtils.dpToPix(30F))
        graphBar.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any?,
                toAppendTo: StringBuffer,
                pos: FieldPosition
            ): StringBuffer {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo.append(domainLabels[i])
            }

            override fun parseObject(p0: String?, p1: ParsePosition?): Any? {
                return null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}