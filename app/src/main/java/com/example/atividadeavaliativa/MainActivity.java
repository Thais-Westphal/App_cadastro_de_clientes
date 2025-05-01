package com.example.atividadeavaliativa;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editNome, editEmail;
    RadioButton radioFeminino, radioMasculino, radioOutro;
    Spinner spinnerEscolaridade;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligação com os elementos da tela
        editNome = findViewById(R.id.editTextText);
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        radioFeminino = findViewById(R.id.radioButton);
        radioMasculino = findViewById(R.id.radioButton2);
        radioOutro = findViewById(R.id.radioButton3);
        spinnerEscolaridade = findViewById(R.id.spinnerEscolaridade);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        // Preenchendo o Spinner com opções
        String[] escolaridades = {"Fundamental", "Médio", "Superior", "Outros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                escolaridades
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEscolaridade.setAdapter(adapter);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores inseridos
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();

                // Validação do nome (apenas letras e espaços)
                if (nome.isEmpty()) {
                    editNome.setError("Nome é obrigatório");
                    return;
                }

                // Verificar se o nome contém números
                if (!nome.matches("[a-zA-Z\\s]+")) {
                    editNome.setError("O nome deve conter apenas letras e espaços");
                    return;
                }

                // Validação do e-mail
                if (email.isEmpty()) {
                    editEmail.setError("Email é obrigatório");
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editEmail.setError("Email inválido");
                    return;
                }

                // Determinar o sexo selecionado
                String sexo = "";
                if (radioFeminino.isChecked()) {
                    sexo = "Feminino";
                } else if (radioMasculino.isChecked()) {
                    sexo = "Masculino";
                } else if (radioOutro.isChecked()) {
                    sexo = "Outro";
                }

                // Obter a escolaridade selecionada
                String escolaridade = spinnerEscolaridade.getSelectedItem().toString();

                // Exibir dados no Toast
                String mensagem = "Nome: " + nome +
                        "\nEmail: " + email +
                        "\nSexo: " + sexo +
                        "\nEscolaridade: " + escolaridade;

                Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }
}
