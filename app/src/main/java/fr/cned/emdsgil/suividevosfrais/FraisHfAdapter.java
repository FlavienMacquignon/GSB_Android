package fr.cned.emdsgil.suividevosfrais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

class FraisHfAdapter extends BaseAdapter {

    private final ArrayList<FraisHf> lesFrais; // liste des frais du mois
    private final LayoutInflater inflater;
    private final Context context;

    /**
	 * Constructeur de l'adapter pour valoriser les propriétés
     * @param context Accès au contexte de l'application
     * @param lesFrais Liste des frais hors forfait
     */
    public FraisHfAdapter(Context context, ArrayList<FraisHf> lesFrais) {
        inflater = LayoutInflater.from(context) ;
        this.lesFrais = lesFrais ;
        this.context = context;
    }

    /**
     * retourne le nombre d'éléments de la listview
     */
    @Override
    public int getCount() {
        return lesFrais.size();
    }

    /**
     * retourne l'item de la listview à un index précis
     */
    @Override
    public Object getItem(int index) {
        return lesFrais.get(index);
    }

    /**
     * retourne l'index de l'élément actuel
     */
    @Override
    public long getItemId(int index) {
        return index;
    }

    private void removeline(int index) {
        FraisHf leFrais= (FraisHf) getItem(index);
        Hashtable<?, ?> monHash = (Hashtable<?, ?>) Serializer.deSerialize(context);
        Hashtable<Integer, FraisMois> monHashCast = new Hashtable<>();
        for (Hashtable.Entry<?, ?> entry : monHash.entrySet()) {
            Integer i = 0;
            do {
                if (((FraisMois) entry.getValue()).getLesFraisHf().get(i).equals(leFrais)) {
                    FraisMois unFraisMois = ((FraisMois) entry.getValue());
                    ArrayList <FraisHf> lesFraisHf= unFraisMois.getLesFraisHf();
                    lesFraisHf.remove(index);
                    lesFrais.remove(index);
                    unFraisMois.setLesFraisHf(lesFraisHf);
                    Integer key= (Integer) entry.getKey();
                    monHashCast.remove(entry.getKey());
                    monHashCast.put((Integer) entry.getKey(), unFraisMois);
                } else {
                    monHashCast.put((Integer) entry.getKey(), (FraisMois) entry.getValue());
                }
                i++;
            } while (i < ((FraisMois) entry.getValue()).getLesFraisHf().size());

        }
        Global.listFraisMois = monHashCast;
        Serializer.serialize(monHashCast, context);
    }

    /**
     * Affichage dans la liste
     */
    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_liste, parent, false);
            holder.txtListJour = convertView.findViewById(R.id.txtListJour);
            holder.txtListMontant = convertView.findViewById(R.id.txtListMontant);
            holder.txtListMotif = convertView.findViewById(R.id.txtListMotif);
            holder.cmdSuppHf = convertView.findViewById(R.id.cmdSuppHf);
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtListJour.setText(String.format(Locale.FRANCE, "%d", lesFrais.get(index).getJour()));
        holder.txtListMontant.setText(String.format(Locale.FRANCE, "%.2f", lesFrais.get(index).getMontant()));
        holder.txtListMotif.setText(lesFrais.get(index).getMotif());
        holder.cmdSuppHf.setTag(index);
        holder.cmdSuppHf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeline(index);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    /**
     * structure contenant les éléments d'une ligne
     */
    private class ViewHolder {
        TextView txtListJour;
        TextView txtListMontant;
        TextView txtListMotif;
        ImageButton cmdSuppHf;
    }

}
