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

package com.kivi.framework.echarts.samples.funnel;

import com.kivi.framework.echarts.Label;
import com.kivi.framework.echarts.LabelLine;
import com.kivi.framework.echarts.code.*;
import com.kivi.framework.echarts.data.Data;
import com.kivi.framework.echarts.series.Funnel;
import com.kivi.framework.echarts.style.LineStyle;
import com.kivi.framework.echarts.style.TextStyle;
import com.kivi.framework.echarts.util.EnhancedOption;
import org.junit.Test;

/**
 * 
 */
public class FunnelTest {

    @Test
    public void test() {
        //地址：http://echarts.baidu.com/doc/example/funnel.html
        EnhancedOption option = new EnhancedOption();
        option.title().text("漏斗图").subtext("纯属虚构");
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c}%");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);
        option.legend("展现", "点击", "访问", "咨询", "订单");
        option.calculable(true);

        Funnel funnel = new Funnel("漏斗图");
        funnel.x("10%").y(60).width("80%").
                min(0).max(100).
                minSize("0%").maxSize("100%").
                sort(Sort.descending).
                gap(10);
        funnel.itemStyle().normal().borderColor("#fff").borderWidth(1).
                label(new Label().show(true).position(Position.inside)).
                labelLine(new LabelLine().show(false).length(10).lineStyle(new LineStyle().width(1).type(LineType.solid)));
        funnel.itemStyle().emphasis().borderColor("red").borderWidth(5).
                label(new Label().show(true).formatter("{b}:{c}%").textStyle(new TextStyle().fontSize(20))).
                labelLine(new LabelLine().show(true));

        funnel.data(new Data().value(60).name("访问"),
                new Data().value(40).name("咨询"),
                new Data().value(20).name("订单"),
                new Data().value(80).name("点击"),
                new Data().value(100).name("展现")
        );

        option.series(funnel);
        option.exportToHtml("funnel.html");
        option.view();
    }
}
