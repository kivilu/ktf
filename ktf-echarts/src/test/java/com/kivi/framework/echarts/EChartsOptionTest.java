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

package com.kivi.framework.echarts;

import org.junit.Test;

import com.kivi.framework.echarts.axis.CategoryAxis;
import com.kivi.framework.echarts.axis.ValueAxis;
import com.kivi.framework.echarts.code.MarkType;
import com.kivi.framework.echarts.code.Tool;
import com.kivi.framework.echarts.code.Trigger;
import com.kivi.framework.echarts.data.LineData;
import com.kivi.framework.echarts.series.Line;
import com.kivi.framework.echarts.util.EnhancedOption;

/**
 * Description: OptionTest
 *
 * 
 */
public class EChartsOptionTest {

    @Test
    public void basicOption() {
        EnhancedOption option = new EnhancedOption();
        option.legend().padding(5).itemGap(10).data("ios7", "android4");
        option.toolbox().show(true).feature(Tool.dataView, Tool.saveAsImage, Tool.dataZoom, Tool.magicType);
        option.tooltip().trigger(Trigger.item);
        option.xAxis(new CategoryAxis().data("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
                "Nov", "Dec"));
        option.yAxis(new ValueAxis().boundaryGap(new Double[] { 0.1, 0.1 }).splitNumber(10));

        Line line = new Line();
        line.name("ios7").data(112, 23, 45, 56, 233, 343, 454, 89, 343, 123, 45, 123).markLine()
                .data(new LineData().type(MarkType.average).name("ios7"));
        option.series(line);

        line = new Line();
        line.name("android4").data(45, 123, 145, 526, 233, 343, 44, 829, 33, 123, 45, 13).itemStyle().normal().label()
                .show(true);
        option.series(line);

        option.view();
    }
}
