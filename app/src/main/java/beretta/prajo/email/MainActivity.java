package beretta.prajo.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = findViewById(R.id.btnEnviar);
        //Definicao da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtendo dados digitados pelo usuario
                EditText etEmail = findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                //Criando uma intencao de enviar algo a outra tela
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //Definindo que o interesse eh em usar apps que enviam e recebem e-mail
                i.setData(Uri.parse("mailto:"));

                //Preenchendo a Intent com os dados que o usuario quer enviar para a app externa
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //executando o Intent
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                //Tratando possivel erro ao executar o Intent
                //Msg de erro
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}