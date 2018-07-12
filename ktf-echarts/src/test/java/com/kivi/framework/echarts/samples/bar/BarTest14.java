/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kivi.framework.echarts.samples.bar;

import com.kivi.framework.echarts.axis.CategoryAxis;
import com.kivi.framework.echarts.axis.ValueAxis;
import com.kivi.framework.echarts.code.*;
import com.kivi.framework.echarts.data.PointData;
import com.kivi.framework.echarts.feature.MagicType;
import com.kivi.framework.echarts.series.Bar;
import com.kivi.framework.echarts.util.EnhancedOption;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class BarTest14 {

    @Test
    public void test() {
        //地址：http://echarts.baidu.com/echarts2/doc/example/bar14.html
        EnhancedOption option = new EnhancedOption();
        option.title().text("ECharts例子个数统计").subtext("Rainbow bar example")
            .link("http://echarts.baidu.com/doc/example.html").x(X.center);
        option.tooltip().trigger(Trigger.item);
        option.calculable(true);
        option.grid().borderWidth(0).y(80).y2(60);
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        option.xAxis(new CategoryAxis().data("Line", "Bar", "Scatter", "K", "Pie", "Radar", "Chord", "Force", "Map", "Gauge", "Funnel"));
        option.yAxis(new ValueAxis().show(false));

        Bar bar = new Bar("ECharts例子个数统计");
        bar.itemStyle().normal().color("function(params) {" +
                "                        var colorList = [" +
                "                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'," +
                "                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD'," +
                "                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'" +
                "                        ];" +
                "                        return colorList[params.dataIndex]" +
                "                    }")
                .label().show(true).position(Position.top).formatter("{b}\n{c}");
        bar.data(12,21,10,4,12,5,6,5,25,23,7);

        option.series(bar);
        option.exportToHtml("bar14.html");
        option.view();
    }
}
