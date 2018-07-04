package com.ktf.parser.test.converters.testobjects;

import java.util.Date;

import com.ktf.parser.annotations.DelimitedRecord;
import com.ktf.parser.annotations.FieldConverter;
import com.ktf.parser.enums.ConverterKind;

@DelimitedRecord( "," )
public class DateFormatType2 {
    public int  orderID;
    public int  employeeID;
    @FieldConverter( converter = ConverterKind.Date, format = "M-d-yyyy" )
    public Date orderDate;
    @FieldConverter( converter = ConverterKind.Date, format = "MMddyyyy" )
    public Date requiredDate;
    @FieldConverter( converter = ConverterKind.Date, format = "M/d/yy" )
    public Date shippedDate;
}
