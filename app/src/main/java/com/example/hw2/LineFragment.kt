package com.example.hw2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import kotlinx.android.synthetic.main.fragment_line.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class LineFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        val series1Format = LineAndPointFormatter(Color.RED, Color.RED, null, null)
        val series2Format = LineAndPointFormatter(Color.GREEN, Color.GREEN, null, null)
        val series3Format = LineAndPointFormatter(Color.BLUE, Color.BLUE, null, null)

        series1Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                100,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        series2Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                100,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        series3Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                100,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        graphLine.addSeries(series1, series1Format)
        graphLine.addSeries(series2, series2Format)
        graphLine.addSeries(series3, series3Format)
        graphLine.linesPerDomainLabel = 1

        graphLine.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
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
        return inflater.inflate(R.layout.fragment_line, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LineFragment().apply {

            }
    }
}