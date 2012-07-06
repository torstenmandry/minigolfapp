package de.javandry.minigolfapp.parser;

import android.content.Context;
import android.util.Log;
import de.javandry.minigolfapp.entities.Ball;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONBalllisteParser implements BalllisteParser {
    private static final String LOG_TAG = "JSONBalllisteParser";

    private JSONArray jsonArray;

    public JSONBalllisteParser(Context context, String filename) throws BalllisteParserException {
        try {
            jsonArray = new JSONArray(readJSONBallliste(context, filename));
        } catch (JSONException e) {
            throw new BalllisteParserException(e);
        }
    }

    private String readJSONBallliste(Context context, String filename) {
        Log.d(LOG_TAG, "Read JSON Ball-Liste");
        StringBuffer content = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String nextLine = reader.readLine();
            while (nextLine != null) {
                Log.d(LOG_TAG, nextLine);
                content.append(nextLine).append("\n");
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Fehler beim einlesen der JSON Ball-Liste", e);
        }
        return content.toString();
    }

    @Override
    public int getNumberOfBalls() {
        return jsonArray.length();
    }

    @Override
    public Ball getBall(int i) throws BalllisteParserException {
        try {
            JSONObject nextJsonBall = jsonArray.getJSONObject(i);
            return parseBall(nextJsonBall);
        } catch (JSONException e) {
            throw new BalllisteParserException(e);
        }
    }

    private Ball parseBall(JSONObject nextJsonBall) throws JSONException {
        Ball ball = new Ball();
        ball.setId(nextJsonBall.getInt("id"));
        ball.setHersteller(nextJsonBall.getString("hrst"));
        ball.setName(nextJsonBall.getString("name"));
        ball.setGroesse(nextJsonBall.getString("gr"));
        ball.setLackierung(nextJsonBall.getString("l"));
        ball.setSprunghoehe(nextJsonBall.getString("s") == null || nextJsonBall.getString("s").length() == 0 ? null
                : Double.valueOf(nextJsonBall.getString("s")));
        ball.setHaerte(nextJsonBall.getString("h") == null || nextJsonBall.getString("h").length() == 0 ? null
                : Double.valueOf(nextJsonBall.getString("h")));
        ball.setGewicht(nextJsonBall.getString("gw") == null || nextJsonBall.getString("gw").length() == 0 ? null
                : Double.valueOf(nextJsonBall.getString("gw")));
        return ball;
    }

}