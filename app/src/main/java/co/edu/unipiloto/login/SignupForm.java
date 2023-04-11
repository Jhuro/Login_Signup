package co.edu.unipiloto.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignupForm extends AppCompatActivity {

    private DatabaseHelper myDB;
    private TextInputLayout editFullName, editUsername, editEmail, editPassword, editPasswordConfirm, editLatitud, editLongitud, editEdad;
    private Spinner spnRoles;
    private Button btnAddData, btnViewAll;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        myDB=new DatabaseHelper(this);
        editFullName = (TextInputLayout) findViewById(R.id.et_fullName);
        editUsername = (TextInputLayout) findViewById(R.id.et_fullName);
        editEmail = (TextInputLayout) findViewById(R.id.et_fullName);
        editPassword = (TextInputLayout) findViewById(R.id.et_fullName);
        editPasswordConfirm = (TextInputLayout) findViewById(R.id.et_passwordConfirm);
        editLatitud = (TextInputLayout) findViewById(R.id.et_latitud);
        editLongitud = (TextInputLayout) findViewById(R.id.et_longitud);
        editEdad = (TextInputLayout) findViewById(R.id.et_edad);
        spnRoles = (Spinner) findViewById(R.id.spn_rol);
        btnAddData = (Button) findViewById(R.id.btn_register);
        btnViewAll = (Button) findViewById(R.id.btn_users);

        addData();
        viewAll();
    }

    public void addData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!editPassword.getEditText().getText().toString().equals(editPasswordConfirm.getEditText().getText().toString())){
                            Toast.makeText(SignupForm.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(Integer.parseInt(editEdad.getEditText().getText().toString()) < 18){
                            Toast.makeText(SignupForm.this, "Debe tener más de 18 años.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Integer valueInt = new Integer(gender);
                        Double latitudDouble = Double.parseDouble(editLatitud.getEditText().getText().toString());
                        Double longitudDouble = Double.parseDouble(editLongitud.getEditText().getText().toString());
                        Integer edadInt = Integer.parseInt(editEdad.getEditText().getText().toString());
                        Usuario user = new Usuario();
                        user.setFullName(editFullName.getEditText().getText().toString());
                        user.setUsername(editUsername.getEditText().getText().toString());
                        user.setEmail(editEmail.getEditText().getText().toString());
                        user.setPassword(editPassword.getEditText().getText().toString());
                        user.setLatitud(latitudDouble);
                        user.setLongitud(longitudDouble);
                        user.setEdad(edadInt);
                        user.setRol(String.valueOf(spnRoles.getSelectedItem()));
                        user.setGender(valueInt);
                        boolean isInserted= myDB.insertData(user);
                        if(isInserted)
                            Toast.makeText(SignupForm.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(SignupForm.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDB.getAllData();
                        if(res.getCount()==0) {
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id: " + res.getString(0)+"\n");
                            buffer.append("Name: " + res.getString(1)+"\n");
                            buffer.append("Username: " + res.getString(2)+"\n");
                            buffer.append("EMail: " + res.getString(3)+"\n");
                            buffer.append("Password: " + res.getString(4)+"\n");
                            buffer.append("Latitud: " + res.getString(5)+"\n");
                            buffer.append("Longitud: " + res.getString(6)+"\n");
                            buffer.append("Edad: " + res.getString(7)+"\n");
                            buffer.append("Rol: " + res.getString(8)+"\n");
                            buffer.append("Gender: " + res.getString(9)+"\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rb_male:
                if(checked)
                    gender=1;
                break;
            case R.id.rb_female:
                if(checked)
                    gender=0;
                break;
        }
    }
}