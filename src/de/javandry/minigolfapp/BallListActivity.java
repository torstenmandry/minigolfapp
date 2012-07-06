package de.javandry.minigolfapp;

import android.app.Dialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import de.javandry.minigolfapp.db.BaelleTable;
import de.javandry.minigolfapp.db.BallDbAdapter;

public class BallListActivity extends ListActivity
{
    BallDbAdapter ballDbAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ballDbAdapter = new BallDbAdapter(this);
        ballDbAdapter.open();

        setListAdapter(getBallListAdapter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ballDbAdapter.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //set up dialog
        Dialog dialog = new Dialog(BallListActivity.this);
        dialog.setContentView(R.layout.balldetails);
        dialog.setTitle(((TextView) v.findViewById(R.id.balllist_item_fullname)).getText());
        ((ImageView) dialog.findViewById(R.id.balldetails_image)).setImageResource(R.drawable.unknown_ball);
        ((TextView) dialog.findViewById(R.id.balldetails_sprunghoehe)).setText(((TextView) v.findViewById(R.id.balllist_item_sprunghoehe)).getText());
        ((TextView) dialog.findViewById(R.id.balldetails_gewicht)).setText(((TextView) v.findViewById(R.id.balllist_item_gewicht)).getText());
        ((TextView) dialog.findViewById(R.id.balldetails_haerte)).setText(((TextView) v.findViewById(R.id.balllist_item_haerte)).getText());
        dialog.setCancelable(true);
        dialog.show();
    }

    /**
     * Gibt den BallListAdapter zurück.
     * @return der BallListAdapter.
     */
    private ListAdapter getBallListAdapter() {
        Cursor cursor = ballDbAdapter.findAllBalls();
        startManagingCursor(cursor);

        String[] from = new String[] { BaelleTable.COL_FULLNAME, BaelleTable.COL_SPRUNGHOEHE, BaelleTable.COL_HAERTE,
                BaelleTable.COL_GEWICHT };
        int[] to = new int[] { R.id.balllist_item_fullname, R.id.balllist_item_sprunghoehe, R.id.balllist_item_haerte,
                R.id.balllist_item_gewicht };

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter baelle =
                new SimpleCursorAdapter(this, R.layout.balllist_item, cursor, from, to);

        return baelle;
    }
}
