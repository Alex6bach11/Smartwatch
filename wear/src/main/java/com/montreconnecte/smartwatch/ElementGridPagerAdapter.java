package com.montreconnecte.smartwatch;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.*;
import java.util.*;

/**
 * Created by baptiste on 14/04/16.
 */
public class ElementGridPagerAdapter extends FragmentGridPagerAdapter {

    private List<Row> mRows = new ArrayList<Row>();
    private List<Element> mElements;

    public ElementGridPagerAdapter(List<Element> elements, FragmentManager fm) {
        super(fm);

        this.mRows = new ArrayList<Row>();

        //Construit le tableau des éléménts à afficher
        for (Element element : elements) {
            mRows.add(new Row(
                            //pour l'instant nous ne mettrons qu'un élément par ligne
                            CardFragment.create(element.getTitre(),  element.getDescription()) //element.getTexte())
                    )
            );
        }
    }

    //le drawable affichée en background pour la ligne [row]
    @Override
    public Drawable getBackgroundForRow(final int row) {
        //return DrawableCache.getInstance().get(row); //les images ont déjà été chargées dans un LruCache
        return new Drawable() {
            @Override
            public void draw(Canvas canvas) {

            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
    }

    //Le fragment à afficher p
    @Override
    public Fragment getFragment(int row, int col) {
        Row adapterRow = mRows.get(row);
        return adapterRow.getColumn(col);
    }

    @Override
    public Drawable getBackgroundForPage(final int row, final int column) {
        //nous pouvons spécifier un drawable différent pour le swipe horizontal
        return getBackgroundForRow(row);
    }

    //Le nombre de lignes dans la grille
    @Override
    public int getRowCount() {
        return mRows.size();
    }

    //Le nombre de colonnes par ligne
    @Override
    public int getColumnCount(int rowNum) {
        return mRows.get(rowNum).getColumnCount();
    }

    /**
     * Représentation d'une ligne - Contient une liste de fragments
     */
    private class Row {
        final List<Fragment> columns = new ArrayList<Fragment>();
        public Row(Fragment... fragments) {
            for (Fragment f : fragments) {
                add(f);
            }
        }
        public void add(Fragment f) {
            columns.add(f);
        }
        Fragment getColumn(int i) {
            return columns.get(i);
        }
        public int getColumnCount() {
            return columns.size();
        }
    }
}
