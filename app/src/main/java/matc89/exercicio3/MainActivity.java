package matc89.exercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    private EditText editDescricao = null;
    private EditText editPrioridade = null;
    private Button buttonRemover = null;
    private ListView listView = null;
    private TarefaAdapter listAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDescricao = (EditText)findViewById(R.id.editDescricao);
        editPrioridade = (EditText)findViewById(R.id.editPrioridade);
        listView = (ListView)findViewById(R.id.listView);

        buttonRemover = (Button)findViewById(R.id.buttonRemover);

        listAdapter = new TarefaAdapter(this, (List)tarefas);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tarefas.remove(position);
                listAdapter.notifyDataSetChanged();
            }
        });

        buttonRemover.setEnabled(false);
    }

    public void adicionar(View v) {
        String description = editDescricao.getText().toString();
        String priorityString = editPrioridade.getText().toString();

        if (priorityString.equals("") || description.equals("")) {
            return;
        }

        for (Tarefa t: tarefas) {
            if (t.getDescricao().equals(description)) {
                Toast toast = Toast.makeText(this, "Tarefa já cadastrada.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }

        int priority = Integer.parseInt(editPrioridade.getText().toString());

        if (priority <= 0 || priority > 10) {
            Toast toast = Toast.makeText(this, "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        tarefas.add(new Tarefa(description, priority));

        Collections.sort(tarefas);

        for (Tarefa t : tarefas) {
            System.out.println(t.getPrioridade());
        }

        editDescricao.setText("");
        editPrioridade.setText("");

        buttonRemover.setEnabled(true);

        listAdapter.notifyDataSetChanged();
    }

    public void remover(View v) {
        if (!tarefas.isEmpty()) {
            tarefas.remove(0);
            listAdapter.notifyDataSetChanged();
        }

        if (tarefas.isEmpty()) {
            buttonRemover.setEnabled(false);
        }
    }
}
