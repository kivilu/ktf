package com.ktf.parser.test.converters.testobjects;

import com.ktf.parser.annotations.DelimitedRecord;

@DelimitedRecord( "|" )
public class DecimalType {
    public int    intField;
    public float  floatField;
    public double doubleField;
    // added just for C# compatibility
    public float  decimalField;
}
