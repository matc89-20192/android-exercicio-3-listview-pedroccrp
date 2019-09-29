package matc89.exercicio3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {
    public TarefaAdapter(@NonNull Context context, List<Tarefa> tarefas) {
        super(context, 0, tarefas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        ViewHolder holder;

        Tarefa tarefa = getItem(position);

        if (tarefa != null) {
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(android.R.layout.simple_list_item_2, null);
                holder = new ViewHolder();
                holder.textDescription = (TextView)view.findViewById(android.R.id.text1);
                holder.textPriority = (TextView)view.findViewById(android.R.id.text2);

                view.setTag(holder);
            }

            holder = (ViewHolder)view.getTag();

            holder.textDescription.setText(tarefa.getDescricao());
            holder.textPriority.setText("Prioridade: " + Integer.toString(tarefa.getPrioridade()));
        }

        return view;
    }

    static class ViewHolder {
        public TextView textDescription;
        public TextView textPriority;

    }
}


