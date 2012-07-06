package de.javandry.minigolfapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.javandry.minigolfapp.entities.Ball;
import de.javandry.minigolfapp.parser.BalllisteParser;
import de.javandry.minigolfapp.parser.BalllisteParserException;
import de.javandry.minigolfapp.parser.JSONBalllisteParser;

/**
 * Adapter zum Zugriff auf die SQLite Ball Datenbank.
 * @author TMA
 */
public class BallDbAdapter {
    private static final String LOG_TAG = "BallDbAdapter";

    private static final String DATABASE_NAME = "balldb";

    private static final int DATABASE_VERSION = 1;

    private final Context context;

    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private final Context context;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "Create Database...");
            db.execSQL(databaseCreateString());
            createData(db);
        }

        private void createData(SQLiteDatabase db) {
            Log.d(LOG_TAG, "Create Data...");
            try {
                BalllisteParser parser = new JSONBalllisteParser(context, "ballliste.json");
                for (int i = 0; i < parser.getNumberOfBalls(); i++) {
                    Ball nextBall = parser.getBall(i);
                    Log.d(LOG_TAG, nextBall.getId() + " - " +
                            nextBall.getHersteller() + " - " +
                            nextBall.getName());
                    insertBall(db, nextBall);
                }
            } catch (BalllisteParserException e) {
                Log.e(LOG_TAG, "Fehler beim parsen der JSON Ball-Liste", e);
            }
        }

        private void insertBall(SQLiteDatabase db, Ball ball) {
            insertBall(
                    db,
                    ball.getId(),
                    ball.getHersteller(),
                    ball.getName(),
                    ball.getGroesse(),
                    ball.getLackierung(),
                    ball.getSprunghoehe(),
                    ball.getHaerte(),
                    ball.getGewicht());
        }

        public void insertBall(SQLiteDatabase db, int id, String hersteller, String name,
                               String groesse, String lackierung, Double sprunghoehe,
                               Double haerte, Double gewicht) {
            db.execSQL(BaelleTable.insertString(), new String[] {
                    String.valueOf(id),
                    hersteller,
                    name,
                    groesse,
                    lackierung,
                    (sprunghoehe == null ? null : String.valueOf(sprunghoehe)),
                    (haerte == null ? null : String.valueOf(haerte)),
                    (gewicht == null ? null : String.valueOf(gewicht))
            });
        }

        private String databaseCreateString() {
            return BaelleTable.createString();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion);
            //      db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAELLE);
            //      onCreate(db);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            Log.w(LOG_TAG, "Opening database");
            recreateDb(db);
        }

        private void recreateDb(SQLiteDatabase db) {
            db.execSQL(BaelleTable.dropString());
            onCreate(db);
        }

    }

    public BallDbAdapter(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getReadableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public Cursor findAllBalls() {
        return database.rawQuery(
                BaelleTable.defaultSelectString(),
                null);
    }

    public void clearAll() {
        database.execSQL(BaelleTable.deleteAllString());
    }

}