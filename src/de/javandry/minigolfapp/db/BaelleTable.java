package de.javandry.minigolfapp.db;

public class BaelleTable {

    private static final String TABLENAME = "baelle";
    public static final String COL_ID = "_id";
    public static final String COL_HERSTELLER = "hersteller";
    public static final String COL_NAME = "name";
    public static final String COL_GROESSE = "groesse";
    public static final String COL_LACKIERUNG = "lackierung";
    public static final String COL_SPRUNGHOEHE = "sprunghoehe";
    public static final String COL_HAERTE = "haerte";
    public static final String COL_GEWICHT = "gewicht";
    public static final String COL_FULLNAME = "fullname";

    public static String createString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE " + TABLENAME + " ( ");
        builder.append(COL_ID + " INTEGER PRIMARY KEY, ");
        builder.append(COL_HERSTELLER + " TEXT, ");
        builder.append(COL_NAME + " TEXT NOT NULL, ");
        builder.append(COL_GROESSE + " TEXT, ");
        builder.append(COL_LACKIERUNG + " TEXT, ");
        builder.append(COL_SPRUNGHOEHE + " REAL, ");
        builder.append(COL_HAERTE + " REAL, ");
        builder.append(COL_GEWICHT + " REAL );");
        String createString = builder.toString();
        return createString;
    }

    public static String dropString() {
        return "DROP TABLE IF EXISTS " + TABLENAME;
    }

    public static String insertString() {
        String insert = "INSERT INTO " + TABLENAME + " ( " +
                COL_ID + ", " +
                COL_HERSTELLER + ", " +
                COL_NAME + ", " +
                COL_GROESSE + ", " +
                COL_LACKIERUNG + ", " +
                COL_SPRUNGHOEHE + ", " +
                COL_HAERTE + ", " +
                COL_GEWICHT + " ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? );";
        return insert;
    }

    public static String defaultSelectString() {
        return "SELECT " + COL_ID + ", " +
                COL_HERSTELLER + ", " +
                COL_NAME + ", " +
                COL_GROESSE + ", " +
                COL_LACKIERUNG + ", " +
                COL_SPRUNGHOEHE + ", " +
                COL_HAERTE + ", " +
                COL_GEWICHT + ", " +
                COL_HERSTELLER + " || ' ' || " + COL_NAME + " || ' (' || " + COL_GROESSE + " || " + COL_LACKIERUNG
                + " || ')' AS " + COL_FULLNAME + " " +
                "FROM " + TABLENAME + " ORDER BY " + COL_SPRUNGHOEHE + " ASC";
    }

    public static String deleteAllString() {
        return "DELETE FROM " + TABLENAME;
    }
}