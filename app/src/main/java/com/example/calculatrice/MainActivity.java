package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables pour l'affichage
    private TextView tvAffichage;

    // Variables pour les calculs
    private double nombre1 = 0;
    private double nombre2 = 0;
    private String operateur = "";
    private boolean nouvelleOperation = true;

    // Boutons
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnPlus, btnMoins, btnMultiplier, btnDiviser;
    private Button btnEgal, btnEffacer, btnVirgule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'affichage
        tvAffichage = findViewById(R.id.tvAffichage);

        // Initialisation des boutons chiffres
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        // Initialisation des boutons opérations
        btnPlus = findViewById(R.id.btnPlus);
        btnMoins = findViewById(R.id.btnMoins);
        btnMultiplier = findViewById(R.id.btnMultiplier);
        btnDiviser = findViewById(R.id.btnDiviser);
        btnEgal = findViewById(R.id.btnEgal);
        btnEffacer = findViewById(R.id.btnEffacer);
        btnVirgule = findViewById(R.id.btnVirgule);

        // Ajouter les listeners
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnMoins.setOnClickListener(this);
        btnMultiplier.setOnClickListener(this);
        btnDiviser.setOnClickListener(this);
        btnEgal.setOnClickListener(this);
        btnEffacer.setOnClickListener(this);
        btnVirgule.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // Gestion des chiffres
        if (id == R.id.btn0) ajouterChiffre("0");
        else if (id == R.id.btn1) ajouterChiffre("1");
        else if (id == R.id.btn2) ajouterChiffre("2");
        else if (id == R.id.btn3) ajouterChiffre("3");
        else if (id == R.id.btn4) ajouterChiffre("4");
        else if (id == R.id.btn5) ajouterChiffre("5");
        else if (id == R.id.btn6) ajouterChiffre("6");
        else if (id == R.id.btn7) ajouterChiffre("7");
        else if (id == R.id.btn8) ajouterChiffre("8");
        else if (id == R.id.btn9) ajouterChiffre("9");

            // Gestion de la virgule
        else if (id == R.id.btnVirgule) {
            if (!tvAffichage.getText().toString().contains(".")) {
                tvAffichage.setText(tvAffichage.getText() + ".");
            }
        }

        // Gestion des opérations
        else if (id == R.id.btnPlus) choisirOperation("+");
        else if (id == R.id.btnMoins) choisirOperation("-");
        else if (id == R.id.btnMultiplier) choisirOperation("×");
        else if (id == R.id.btnDiviser) choisirOperation("÷");

            // Gestion du bouton égal
        else if (id == R.id.btnEgal) {
            calculerResultat();
        }

        // Gestion du bouton effacer
        else if (id == R.id.btnEffacer) {
            effacer();
        }
    }

    // Méthode pour ajouter un chiffre
    private void ajouterChiffre(String chiffre) {
        if (nouvelleOperation) {
            tvAffichage.setText(chiffre);
            nouvelleOperation = false;
        } else {
            if (tvAffichage.getText().toString().equals("0")) {
                tvAffichage.setText(chiffre);
            } else {
                tvAffichage.setText(tvAffichage.getText() + chiffre);
            }
        }
    }

    // Méthode pour choisir une opération
    private void choisirOperation(String op) {
        try {
            nombre1 = Double.parseDouble(tvAffichage.getText().toString());
            operateur = op;
            nouvelleOperation = true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erreur de format", Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour calculer le résultat
    private void calculerResultat() {
        if (!operateur.isEmpty()) {
            try {
                nombre2 = Double.parseDouble(tvAffichage.getText().toString());
                double resultat = 0;

                switch (operateur) {
                    case "+":
                        resultat = nombre1 + nombre2;
                        break;
                    case "-":
                        resultat = nombre1 - nombre2;
                        break;
                    case "×":
                        resultat = nombre1 * nombre2;
                        break;
                    case "÷":
                        if (nombre2 != 0) {
                            resultat = nombre1 / nombre2;
                        } else {
                            Toast.makeText(this, "Division par zéro impossible!",
                                    Toast.LENGTH_LONG).show();
                            effacer();
                            return;
                        }
                        break;
                }

                // Afficher le résultat
                if (resultat == (long) resultat) {
                    tvAffichage.setText(String.valueOf((long) resultat));
                } else {
                    tvAffichage.setText(String.valueOf(resultat));
                }

                operateur = "";
                nouvelleOperation = true;

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Erreur de calcul", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Méthode pour effacer
    private void effacer() {
        tvAffichage.setText("0");
        nombre1 = 0;
        nombre2 = 0;
        operateur = "";
        nouvelleOperation = true;
    }
}