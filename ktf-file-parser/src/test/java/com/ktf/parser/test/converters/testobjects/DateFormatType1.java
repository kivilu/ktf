package com.ktf.parser.test.converters.testobjects;

import java.util.Date;

import com.ktf.parser.annotations.DelimitedRecord;
import com.ktf.parser.annotations.FieldConverter;
import com.ktf.parser.enums.ConverterKind;

@DelimitedRecord
public class DateFormatType1 {
    public int orderID;
    public int employeeID;

    public DateFormatType1() {

    }

    @FieldConverter( converter = ConverterKind.Date, format = "d-M-yyyy" )
    public Date orderDate;
    @FieldConverter( converter = ConverterKind.Date, format = "MMddyyyy" )
    public Date requiredDate;
    @FieldConverter( converter = ConverterKind.Date, format = "d/M/yy" )
    public Date shippedDate;
}
