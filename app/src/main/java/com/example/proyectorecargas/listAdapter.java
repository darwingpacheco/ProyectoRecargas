package com.example.proyectorecargas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {
    private List<DatosVentaBD> mData;
    private LayoutInflater mInflater;
    private Context context;

    public listAdapter (List<DatosVentaBD> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element,null);
        return new listAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final listAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));

    }


    public void setItems(List<DatosVentaBD> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconimagen;
        TextView descripcion, telefono, fecha, precio,operador;

        ViewHolder(View itemView){
            super(itemView);
            iconimagen = itemView.findViewById(R.id.iconimagen);
            descripcion = itemView.findViewById(R.id.descripcion);
            telefono = itemView.findViewById(R.id.telefono);
            fecha = itemView.findViewById(R.id.fecha);
            precio = itemView.findViewById(R.id.precio);
            operador = itemView.findViewById(R.id.operador);
        }

        void bindData(final DatosVentaBD item){
           descripcion.setText(item.getDescripcion());
           telefono.setText(item.getTelefono());
           fecha.setText(item.getFecha());
           precio.setText(item.getValor());
           operador.setText(item.getOperador());
        }
    }

}
