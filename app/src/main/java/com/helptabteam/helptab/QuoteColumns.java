package com.helptabteam.helptab;



import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;



/**
 * Created by Nimit Agg on 10-07-2016.
 */

public class QuoteColumns {
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @AutoIncrement
    public static final String _ID = "_id";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String TITLE = "title";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String DESCRIPTION = "description";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String START = "start";
}
