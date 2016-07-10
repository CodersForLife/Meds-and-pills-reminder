package com.helptabteam.helptab;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Nimit Agg on 10-07-2016.
 */
@Database(version = QuoteDatabase.VERSION)
public class QuoteDatabase {
    private QuoteDatabase(){}

    public static final int VERSION = 1;

    @Table(QuoteColumns.class) public static final String QUOTES = "quotes";
}